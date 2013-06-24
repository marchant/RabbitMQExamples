/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.app;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import er.extensions.appserver.ERXApplication;
import er.rest.routes.ERXRoute;
import er.rest.routes.ERXRouteRequestHandler;
import fr.sophiacom.wowodc.app.rest.controllers.DeviceController;
import fr.sophiacom.wowodc.app.rest.controllers.LogController;
import fr.sophiacom.wowodc.app.rest.controllers.NotificationController;
import fr.sophiacom.wowodc.app.rest.controllers.PagesController;
import fr.sophiacom.wowodc.model.Device;
import fr.sophiacom.wowodc.model.Log;
import fr.sophiacom.wowodc.model.pojo.Notification;
import fr.sophiacom.wowodc.model.pojo.Platform;
import fr.sophiacom.wowodc.rabbit.NotificationSenderConsumer;

public class Application extends ERXApplication {
	public static void main(String[] argv) {
		ERXApplication.main(argv, Application.class);
	}

	private static Connection rabbitConnection;

	public Application() {
		ERXApplication.log.info("Welcome to " + name() + " !");
		/* ** put your initialization code in here ** */
		setAllowsConcurrentRequestHandling(true);

		try {
			initRabbitMQConnection();
			initNotificationRoutes(rabbitConnection);
		} catch (IOException e) {
			throw new RuntimeException("RabbitMQ failed to init");
		}

		ERXRouteRequestHandler restRequestHandler = new ERXRouteRequestHandler();
		restRequestHandler.insertRoute(new ERXRoute("Pages", "", ERXRoute.Method.Get, PagesController.class, "mainPage"));
		ERXRouteRequestHandler.register(restRequestHandler);

    	restRequestHandler.addRoute(new ERXRoute(Device.ENTITY_NAME, "/device", ERXRoute.Method.Post, DeviceController.class, "create"));
    	restRequestHandler.addRoute(new ERXRoute(Device.ENTITY_NAME, "/devices", ERXRoute.Method.Get, DeviceController.class, "index"));
    	restRequestHandler.addRoute(new ERXRoute(Log.ENTITY_NAME, "/logs", ERXRoute.Method.Get, LogController.class, "index"));
    	restRequestHandler.addRoute(new ERXRoute(Notification.ENTITY_NAME, "/device/{device:Device}/notification", ERXRoute.Method.Post, NotificationController.class, "send"));
    	
		setDefaultRequestHandler(restRequestHandler);
	}
	
	@Override
	public void didFinishLaunching() {
		try {
			// start a consumer per platform
			for(Platform p: Platform.allPlatforms())
				new Thread(new NotificationSenderConsumer(rabbitConnection.createChannel(), p)).start();
		} catch(Exception e) { }
	}

	private void initRabbitMQConnection() throws IOException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("wowodc");
		factory.setPassword("wowodc");
		factory.setVirtualHost("wowodc-rabbit-1");
		rabbitConnection = factory.newConnection();
	}

	public static Connection getRabbitMQConnection() throws IOException {
			return rabbitConnection;
	}

	private void initNotificationRoutes(Connection co) throws IOException {
		Channel c = co.createChannel();
		// declare a direct exchange which will be persistent:
		c.exchangeDeclare("notification-exchange", "direct", true);

		// create a queue per platform and bind it to the notification exchange:
		for (Platform platform : Platform.allPlatforms()) {
			c.queueDeclare(platform.toString(), true, false, false, null);
			c.queueBind(platform.toString(), "notification-exchange", platform.toString());
		}

		c.close();
	}
}
