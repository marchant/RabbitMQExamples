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
import fr.sophiacom.wowodc.model.Log;

public class LogController extends BaseRestController {

	public LogController(WORequest request) {
		super(request);
	}
	
	@Override
	protected ERXRestFormat defaultFormat() {
		return ERXRestFormat.json();
	}

	@Override
	public WOActionResults indexAction(){
		return response(Log.fetchAllLogs(this.editingContext()), ERXKeyFilter.filterWithAll());
	}
}
