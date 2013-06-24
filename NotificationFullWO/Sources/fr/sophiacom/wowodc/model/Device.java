/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.model;

import org.apache.log4j.Logger;

import er.extensions.eof.ERXEOControlUtilities;

@SuppressWarnings("serial")
public class Device extends _Device {
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(Device.class);
	
	@Override
	public void validateForSave(){
		ERXEOControlUtilities.validateUniquenessOf(this, USER_KEY);
		super.validateForSave();
	}
}
