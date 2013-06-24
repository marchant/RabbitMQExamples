/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.app.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.eof.ERXKeyFilter;
import er.rest.format.ERXRestFormat;
import er.rest.routes.jsr311.PathParam;
import fr.sophiacom.wowodc.model.Device;
import fr.sophiacom.wowodc.model.Log;
import fr.sophiacom.wowodc.model.pojo.Notification;
import fr.sophiacom.wowodc.notification.NotificationSenderFactory;

public class NotificationController extends BaseRestController {

	public NotificationController(WORequest request) {
		super(request);
	}
	
	// when no extensions (.json / .xml) is given in the URL, choose json 
	@Override
	protected ERXRestFormat defaultFormat() {
		return ERXRestFormat.json();
	}

	public WOActionResults sendAction(@PathParam("device") Device device) throws Throwable {
		Notification notification = create(ERXKeyFilter.filterWithAll());

		int sendingTime = sendNotification(notification, device);
		logNotification(notification, device, sendingTime);

		return response(ERXHttpStatusCodes.OK);
	}
	
	private int sendNotification(Notification notification, Device device){
		return NotificationSenderFactory
				.getNotificationSender(device.platform())
				.sendNotification(device.user(), notification.getMessage());
	}
	
	private void logNotification(Notification notification, Device device, int sendingTime){
		Log log = Log.createLog(editingContext(), sendingTime, device);
		log.setMessage(notification.getMessage());
		editingContext().saveChanges();
	}
}
