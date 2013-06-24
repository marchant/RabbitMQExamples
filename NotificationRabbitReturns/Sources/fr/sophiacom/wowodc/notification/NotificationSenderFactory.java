/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.notification;

import fr.sophiacom.wowodc.model.pojo.Platform;

public class NotificationSenderFactory {
	
	public static NotificationSender getNotificationSender(Platform platform){
		switch (platform){
		case IOS:
			return new IOSNotificationSender();
		case ANDROID:
			return new AndroidNotificationSender();
		case WINDOWS_PHONE:
			return new WindowsPhoneNotificationSender();
		}
		return null;
	}
}
