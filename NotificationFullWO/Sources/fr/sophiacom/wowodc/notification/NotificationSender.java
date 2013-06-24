/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.notification;

import java.util.Random;

public abstract class NotificationSender {
	
	private final Random random = new Random();

	protected abstract int minSendTime();
	protected abstract int maxSendTime();

	public int sendNotification(String user, String message){
		int sendingTime = minSendTime() + random.nextInt(maxSendTime() - minSendTime());

		try {
			Thread.sleep(sendingTime);
		} catch (InterruptedException e) {}
		
		return sendingTime;
	}
}
