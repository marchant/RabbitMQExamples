/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.app.rest.controllers;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;

import er.rest.format.ERXRestFormat;
import fr.sophiacom.wowodc.app.components.Main;

/**
 * This controller is for content that is not EO entities driven, e.g. "static pages"
*/
public class PagesController extends BaseRestController {

	public PagesController(WORequest request) {
		super(request);
	}

	public WOActionResults mainPageAction() {
		return pageWithName(Main.class);
	}

	@Override
	protected ERXRestFormat defaultFormat() {
		return ERXRestFormat.html();
	}

}
