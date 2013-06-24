// DO NOT EDIT.  Make changes to Device.java instead.
package fr.sophiacom.wowodc.model;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.math.*;
import java.util.*;
import org.apache.log4j.Logger;

import er.extensions.eof.*;
import er.extensions.foundation.*;

@SuppressWarnings("all")
public abstract class _Device extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Device";

  // Attribute Keys
  public static final ERXKey<fr.sophiacom.wowodc.model.pojo.Platform> PLATFORM = new ERXKey<fr.sophiacom.wowodc.model.pojo.Platform>("platform");
  public static final ERXKey<String> USER = new ERXKey<String>("user");
  // Relationship Keys

  // Attributes
  public static final String PLATFORM_KEY = PLATFORM.key();
  public static final String USER_KEY = USER.key();
  // Relationships

  private static Logger LOG = Logger.getLogger(_Device.class);

  public Device localInstanceIn(EOEditingContext editingContext) {
    Device localInstance = (Device)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public fr.sophiacom.wowodc.model.pojo.Platform platform() {
    return (fr.sophiacom.wowodc.model.pojo.Platform) storedValueForKey(_Device.PLATFORM_KEY);
  }

  public void setPlatform(fr.sophiacom.wowodc.model.pojo.Platform value) {
    if (_Device.LOG.isDebugEnabled()) {
    	_Device.LOG.debug( "updating platform from " + platform() + " to " + value);
    }
    takeStoredValueForKey(value, _Device.PLATFORM_KEY);
  }

  public String user() {
    return (String) storedValueForKey(_Device.USER_KEY);
  }

  public void setUser(String value) {
    if (_Device.LOG.isDebugEnabled()) {
    	_Device.LOG.debug( "updating user from " + user() + " to " + value);
    }
    takeStoredValueForKey(value, _Device.USER_KEY);
  }


  public static Device createDevice(EOEditingContext editingContext, fr.sophiacom.wowodc.model.pojo.Platform platform
, String user
) {
    Device eo = (Device) EOUtilities.createAndInsertInstance(editingContext, _Device.ENTITY_NAME);    
		eo.setPlatform(platform);
		eo.setUser(user);
    return eo;
  }

  public static ERXFetchSpecification<Device> fetchSpec() {
    return new ERXFetchSpecification<Device>(_Device.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<Device> fetchAllDevices(EOEditingContext editingContext) {
    return _Device.fetchAllDevices(editingContext, null);
  }

  public static NSArray<Device> fetchAllDevices(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Device.fetchDevices(editingContext, null, sortOrderings);
  }

  public static NSArray<Device> fetchDevices(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<Device> fetchSpec = new ERXFetchSpecification<Device>(_Device.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Device> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static Device fetchDevice(EOEditingContext editingContext, String keyName, Object value) {
    return _Device.fetchDevice(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Device fetchDevice(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Device> eoObjects = _Device.fetchDevices(editingContext, qualifier, null);
    Device eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Device that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Device fetchRequiredDevice(EOEditingContext editingContext, String keyName, Object value) {
    return _Device.fetchRequiredDevice(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Device fetchRequiredDevice(EOEditingContext editingContext, EOQualifier qualifier) {
    Device eoObject = _Device.fetchDevice(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Device that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Device localInstanceIn(EOEditingContext editingContext, Device eo) {
    Device localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
