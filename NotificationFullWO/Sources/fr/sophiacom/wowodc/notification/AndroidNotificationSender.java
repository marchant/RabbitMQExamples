/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.notification;

public class AndroidNotificationSender extends NotificationSender {

	@Override
	protected int minSendTime() {
		return 50;
	}

	@Override
	protected int maxSendTime() {
		return 200;
	}

}
