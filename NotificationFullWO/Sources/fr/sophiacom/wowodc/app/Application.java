/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.app;

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

public class Application extends ERXApplication {
	public static void main(String[] argv) {
		ERXApplication.main(argv, Application.class);
	}

	public Application() {
		ERXApplication.log.info("Welcome to " + name() + " !");
		/* ** put your initialization code in here ** */
		setAllowsConcurrentRequestHandling(true);
		
		ERXRouteRequestHandler restRequestHandler = new ERXRouteRequestHandler();
		restRequestHandler.insertRoute(new ERXRoute("Pages", "", ERXRoute.Method.Get, PagesController.class, "mainPage"));
    	ERXRouteRequestHandler.register(restRequestHandler);
    	
    	restRequestHandler.addRoute(new ERXRoute(Device.ENTITY_NAME, "/device", ERXRoute.Method.Post, DeviceController.class, "create"));
    	restRequestHandler.addRoute(new ERXRoute(Device.ENTITY_NAME, "/devices", ERXRoute.Method.Get, DeviceController.class, "index"));
    	restRequestHandler.addRoute(new ERXRoute(Log.ENTITY_NAME, "/logs", ERXRoute.Method.Get, LogController.class, "index"));
    	restRequestHandler.addRoute(new ERXRoute(Notification.ENTITY_NAME, "/device/{device:Device}/notification", ERXRoute.Method.Post, NotificationController.class, "send"));
    	
	    setDefaultRequestHandler(restRequestHandler);
	}
}
