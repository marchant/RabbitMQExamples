/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.app.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.extensions.eof.ERXKeyFilter;
import er.rest.format.ERXRestFormat;
import fr.sophiacom.wowodc.model.Device;

public class DeviceController extends BaseRestController {

	public DeviceController(WORequest request) {
		super(request);
	}
	
	// when no extensions (.json / .xml) is given in the URL, choose json 
	@Override
	protected ERXRestFormat defaultFormat() {
		return ERXRestFormat.json();
	}

	@Override
	public WOActionResults createAction(){
		Device device = create(ERXKeyFilter.filterWithAll());
		editingContext().saveChanges();
		return response(device, ERXKeyFilter.filterWithAll());
	}
	
	@Override
	public WOActionResults indexAction(){
		return response(Device.fetchAllDevices(this.editingContext()), ERXKeyFilter.filterWithAll());
	}
}
