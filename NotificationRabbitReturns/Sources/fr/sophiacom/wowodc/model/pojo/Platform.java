/**
 * 
 * @author Antoine Berry
 * @author Yoann Canal
 * 
 */

package fr.sophiacom.wowodc.model.pojo;

public enum Platform {
	IOS,
	ANDROID,
	WINDOWS_PHONE;
	
	public static Platform[] allPlatforms(){
		return new Platform[] {IOS, ANDROID, WINDOWS_PHONE};
	}
}
