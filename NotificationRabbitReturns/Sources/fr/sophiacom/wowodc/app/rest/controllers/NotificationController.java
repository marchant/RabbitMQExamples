/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.app.rest.controllers;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.extensions.appserver.ERXHttpStatusCodes;
import er.extensions.eof.ERXKeyFilter;
import er.rest.format.ERXRestFormat;
import er.rest.routes.ERXRouteController;
import er.rest.routes.jsr311.PathParam;
import fr.sophiacom.wowodc.app.Application;
import fr.sophiacom.wowodc.model.Device;
import fr.sophiacom.wowodc.model.pojo.Notification;
import fr.sophiacom.wowodc.protobuf.NotificationProto;

public class NotificationController extends ERXRouteController {

	private static Channel channel;

	public NotificationController(WORequest request) {
		super(request);
	}

	// when no extensions (.json / .xml) is given in the URL, choose json 
	@Override
	protected ERXRestFormat defaultFormat() {
		return ERXRestFormat.json();
	}

	private Channel getChannel() throws IOException{
		if (channel == null){
			synchronized (this){
				if (channel == null)
					channel = Application.getRabbitMQConnection().createChannel();
			}
		}
		return channel;
	}

	public WOActionResults sendAction(@PathParam("device") Device device) throws Throwable {
		Notification notification = create(ERXKeyFilter.filterWithAll());

		NotificationProto.Notification.Builder notifProto = NotificationProto.Notification.newBuilder();
		notifProto.setUser(device.user()).setMessage(notification.getMessage());
		
		getChannel().basicPublish("notification-exchange", device.platform().toString(), null, notifProto.build().toByteArray());

		return response(ERXHttpStatusCodes.OK);
	}
}
