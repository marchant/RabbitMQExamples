/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.model.pojo;

import com.webobjects.foundation.NSArray;

import er.extensions.eof.ERXDummyRecord;


public class Notification {
	public static final String ENTITY_NAME = "Notification";
	static {
		NSArray<String> keys = new NSArray<String>(new String[] {"message"});
		ERXDummyRecord.registerDescriptionForClass(Notification.class, keys);
	}

	private String _message;
	public String getMessage() {
		return _message;
	}
	public void setMessage(String message) {
		this._message = message;
	}
}
