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
import fr.sophiacom.wowodc.protobuf.NotificationProto;

public class NotificationLoggerConsumer extends ERXRunnable {


	private final EOEditingContext ec;
	
	private final Channel channel;

	public NotificationLoggerConsumer(Channel channel) throws IOException{
		this.ec = ERXEC.newEditingContext();
		this.channel = channel;
	}

	
	@Override
	public void _run() {
		try {
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume("logger-queue", false, consumer);

			// Consuming loop :
			while (true){
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();

				NotificationProto.Log log = 
						NotificationProto.Log.parseFrom(delivery.getBody());
				String message = log.getNotification().hasMessage()?log.getNotification().getMessage():null;

				logNotification(log.getNotification().getUser(), message, log.getSendingTime());
				
				channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			}

		} catch (Exception e) {}
	}

	private void logNotification(String user, String message, int sendingTime){
		ec.lock();
		try {
			Device device = Device.fetchDevice(ec, Device.USER.eq(user));
			Log log = Log.createLog(ec, sendingTime, device);
			log.setMessage(message);

			ec.saveChanges();
		} finally {
			ec.unlock();
		}
	}
}
