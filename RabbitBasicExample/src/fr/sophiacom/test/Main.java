/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.test;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Main {

	public static Channel channel;

	public static void main(String[] args){
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			factory.setVirtualHost("wowodc-basic");
			factory.setUsername("wowodc");
			factory.setPassword("wowodc");
			Connection connection = factory.newConnection();
			channel = connection.createChannel();

			// exchangeDeclare arguments : (exchange-name, type, durable, auto-delete, arguments)
			channel.exchangeDeclare("my-exchange", "fanout", false, true, null);
			// queueDeclare arguments : (queue-name, durable, exclusive, auto-delete, arguments)
			channel.queueDeclare("my-queue", false, false, true, null);
			// queueBind arguments : (queue-name, exchange-name, routing-key)
			channel.queueBind("my-queue", "my-exchange", "");
			
			new Thread(new Consumer()).start();
			
			int i = 0;
			while (true){
				Thread.sleep(1000);
				publishMessage("message " + i++);
			}
		} catch (Exception e) {}
	}
	//...
	
	// PUBLISHING :
	public static void publishMessage(String message) throws IOException{
		byte[] data = message.getBytes();

		// basicPublish arguments : (exchange-name, routing-key, properties, body)
		channel.basicPublish("my-exchange", "", null, data);
	}

	// CONSUMING :
	public static class Consumer implements Runnable {
		@Override
		public void run() {
			try {
				QueueingConsumer consumer = new QueueingConsumer(channel);

				// basicConsume arguments : (queue-name, auto-ack, arguments)
				channel.basicConsume("my-queue", true, consumer);

				while (true) {
					QueueingConsumer.Delivery delivery = consumer.nextDelivery();
					String message = new String(delivery.getBody());
					System.out.println(" [x] Received '" + message + "'");
				}
			} catch (Exception e) {
			}
		}
	}
}
