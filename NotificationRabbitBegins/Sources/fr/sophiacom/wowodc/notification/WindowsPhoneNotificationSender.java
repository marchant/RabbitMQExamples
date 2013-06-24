/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.notification;

public class WindowsPhoneNotificationSender extends NotificationSender {

	@Override
	protected int minSendTime() {
		return 100;
	}

	@Override
	protected int maxSendTime() {
		return 500;
	}
}
