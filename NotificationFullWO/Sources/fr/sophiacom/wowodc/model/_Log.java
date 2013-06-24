// DO NOT EDIT.  Make changes to Log.java instead.
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
public abstract class _Log extends  ERXGenericRecord {
  public static final String ENTITY_NAME = "Log";

  // Attribute Keys
  public static final ERXKey<String> MESSAGE = new ERXKey<String>("message");
  public static final ERXKey<Integer> SENDING_TIME = new ERXKey<Integer>("sendingTime");
  // Relationship Keys
  public static final ERXKey<fr.sophiacom.wowodc.model.Device> DEVICE = new ERXKey<fr.sophiacom.wowodc.model.Device>("device");

  // Attributes
  public static final String MESSAGE_KEY = MESSAGE.key();
  public static final String SENDING_TIME_KEY = SENDING_TIME.key();
  // Relationships
  public static final String DEVICE_KEY = DEVICE.key();

  private static Logger LOG = Logger.getLogger(_Log.class);

  public Log localInstanceIn(EOEditingContext editingContext) {
    Log localInstance = (Log)EOUtilities.localInstanceOfObject(editingContext, this);
    if (localInstance == null) {
      throw new IllegalStateException("You attempted to localInstance " + this + ", which has not yet committed.");
    }
    return localInstance;
  }

  public String message() {
    return (String) storedValueForKey(_Log.MESSAGE_KEY);
  }

  public void setMessage(String value) {
    if (_Log.LOG.isDebugEnabled()) {
    	_Log.LOG.debug( "updating message from " + message() + " to " + value);
    }
    takeStoredValueForKey(value, _Log.MESSAGE_KEY);
  }

  public Integer sendingTime() {
    return (Integer) storedValueForKey(_Log.SENDING_TIME_KEY);
  }

  public void setSendingTime(Integer value) {
    if (_Log.LOG.isDebugEnabled()) {
    	_Log.LOG.debug( "updating sendingTime from " + sendingTime() + " to " + value);
    }
    takeStoredValueForKey(value, _Log.SENDING_TIME_KEY);
  }

  public fr.sophiacom.wowodc.model.Device device() {
    return (fr.sophiacom.wowodc.model.Device)storedValueForKey(_Log.DEVICE_KEY);
  }
  
  public void setDevice(fr.sophiacom.wowodc.model.Device value) {
    takeStoredValueForKey(value, _Log.DEVICE_KEY);
  }

  public void setDeviceRelationship(fr.sophiacom.wowodc.model.Device value) {
    if (_Log.LOG.isDebugEnabled()) {
      _Log.LOG.debug("updating device from " + device() + " to " + value);
    }
    if (er.extensions.eof.ERXGenericRecord.InverseRelationshipUpdater.updateInverseRelationships()) {
    	setDevice(value);
    }
    else if (value == null) {
    	fr.sophiacom.wowodc.model.Device oldValue = device();
    	if (oldValue != null) {
    		removeObjectFromBothSidesOfRelationshipWithKey(oldValue, _Log.DEVICE_KEY);
      }
    } else {
    	addObjectToBothSidesOfRelationshipWithKey(value, _Log.DEVICE_KEY);
    }
  }
  

  public static Log createLog(EOEditingContext editingContext, Integer sendingTime
, fr.sophiacom.wowodc.model.Device device) {
    Log eo = (Log) EOUtilities.createAndInsertInstance(editingContext, _Log.ENTITY_NAME);    
		eo.setSendingTime(sendingTime);
    eo.setDeviceRelationship(device);
    return eo;
  }

  public static ERXFetchSpecification<Log> fetchSpec() {
    return new ERXFetchSpecification<Log>(_Log.ENTITY_NAME, null, null, false, true, null);
  }

  public static NSArray<Log> fetchAllLogs(EOEditingContext editingContext) {
    return _Log.fetchAllLogs(editingContext, null);
  }

  public static NSArray<Log> fetchAllLogs(EOEditingContext editingContext, NSArray<EOSortOrdering> sortOrderings) {
    return _Log.fetchLogs(editingContext, null, sortOrderings);
  }

  public static NSArray<Log> fetchLogs(EOEditingContext editingContext, EOQualifier qualifier, NSArray<EOSortOrdering> sortOrderings) {
    ERXFetchSpecification<Log> fetchSpec = new ERXFetchSpecification<Log>(_Log.ENTITY_NAME, qualifier, sortOrderings);
    fetchSpec.setIsDeep(true);
    NSArray<Log> eoObjects = fetchSpec.fetchObjects(editingContext);
    return eoObjects;
  }

  public static Log fetchLog(EOEditingContext editingContext, String keyName, Object value) {
    return _Log.fetchLog(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Log fetchLog(EOEditingContext editingContext, EOQualifier qualifier) {
    NSArray<Log> eoObjects = _Log.fetchLogs(editingContext, qualifier, null);
    Log eoObject;
    int count = eoObjects.count();
    if (count == 0) {
      eoObject = null;
    }
    else if (count == 1) {
      eoObject = eoObjects.objectAtIndex(0);
    }
    else {
      throw new IllegalStateException("There was more than one Log that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Log fetchRequiredLog(EOEditingContext editingContext, String keyName, Object value) {
    return _Log.fetchRequiredLog(editingContext, new EOKeyValueQualifier(keyName, EOQualifier.QualifierOperatorEqual, value));
  }

  public static Log fetchRequiredLog(EOEditingContext editingContext, EOQualifier qualifier) {
    Log eoObject = _Log.fetchLog(editingContext, qualifier);
    if (eoObject == null) {
      throw new NoSuchElementException("There was no Log that matched the qualifier '" + qualifier + "'.");
    }
    return eoObject;
  }

  public static Log localInstanceIn(EOEditingContext editingContext, Log eo) {
    Log localInstance = (eo == null) ? null : ERXEOControlUtilities.localInstanceOfObject(editingContext, eo);
    if (localInstance == null && eo != null) {
      throw new IllegalStateException("You attempted to localInstance " + eo + ", which has not yet committed.");
    }
    return localInstance;
  }
}
