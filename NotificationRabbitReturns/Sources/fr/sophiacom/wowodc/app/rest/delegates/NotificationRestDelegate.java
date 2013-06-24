/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.app.rest.delegates;

import com.webobjects.eocontrol.EOClassDescription;

import er.rest.ERXEORestDelegate;
import er.rest.ERXRestContext;
import fr.sophiacom.wowodc.model.pojo.Notification;

public class NotificationRestDelegate extends ERXEORestDelegate {

	@Override
	public Object createObjectOfEntityWithID(EOClassDescription entity, Object id, ERXRestContext context) {
		return new Notification();
	}
}
