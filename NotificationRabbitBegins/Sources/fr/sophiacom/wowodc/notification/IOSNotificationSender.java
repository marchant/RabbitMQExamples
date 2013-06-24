/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.notification;

public class IOSNotificationSender extends NotificationSender {

	@Override
	protected int minSendTime() {
		return 25;
	}

	@Override
	protected int maxSendTime() {
		return 100;
	}

}
