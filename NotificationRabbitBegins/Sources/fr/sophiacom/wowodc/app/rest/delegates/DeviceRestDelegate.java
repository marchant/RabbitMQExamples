/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.app.rest.delegates;

import com.webobjects.eocontrol.EOClassDescription;
import com.webobjects.eocontrol.EOEditingContext;

import er.rest.ERXEORestDelegate;
import er.rest.ERXRestContext;
import fr.sophiacom.wowodc.model.Device;

public class DeviceRestDelegate extends ERXEORestDelegate {

	@Override
	public boolean __hasNumericPrimaryKeys(EOClassDescription classDescription) {
		return false;
	}

	@Override
	public Object objectOfEntityWithID(EOClassDescription entity, Object id,
			ERXRestContext context) {
		EOEditingContext editingContext = context.editingContext();
		if (editingContext == null) {
			throw new IllegalArgumentException("There was no editing context attached to this rest context.");
		}
		return Device.fetchDevice(editingContext, Device.USER.eq((String) id));
	}
}
