/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.rabbit;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;
import com.webobjects.eocontrol.EOEditingContext;

import er.extensions.concurrency.ERXRunnable;
import er.extensions.eof.ERXEC;
import fr.sophiacom.wowodc.model.Device;
import fr.sophiacom.wowodc.model.Log;
import fr.sophiacom.wowodc.model.pojo.Platform;
import fr.sophiacom.wowodc.notification.NotificationSender;
import fr.sophiacom.wowodc.notification.NotificationSenderFactory;
import fr.sophiacom.wowodc.protobuf.NotificationProto.Notification;

public class NotificationSenderConsumer extends ERXRunnable {

	private final NotificationSender notificationSender;
	private final Platform platform;
	private final EOEditingContext ec;

	private final Channel channel;

	public NotificationSenderConsumer(Channel channel, Platform platform) throws IOException{
		this.platform = platform;
		this.notificationSender = NotificationSenderFactory.getNotificationSender(platform);
		this.ec = ERXEC.newEditingContext();
		this.channel = channel;
	}

	@Override
	public void _run(){

		try {
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(platform.toString(), false, consumer);

			// Consuming loop :
			while (true){
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();

				Notification notification = Notification.parseFrom(delivery.getBody());

				String message = notification.hasMessage()?notification.getMessage():null;
				int sendingTime = notificationSender.sendNotification(notification.getUser(), message);

				logNotification(notification, sendingTime);
				
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}

		} catch (Exception e) {}
	}

	private void logNotification(Notification notification, int sendingTime){
		ec.lock();
		try {
			String message = notification.hasMessage()?notification.getMessage():null;

			Device device = Device.fetchDevice(ec, Device.USER.eq(notification.getUser()));
			Log log = Log.createLog(ec, sendingTime, device);
			log.setMessage(message);
			ec.saveChanges();
		} finally {
			ec.unlock();
		}
	}
}
