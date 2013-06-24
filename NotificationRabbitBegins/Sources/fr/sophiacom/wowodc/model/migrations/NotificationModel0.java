package fr.sophiacom.wowodc.model.migrations;

import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.foundation.NSArray;

import er.extensions.migration.ERXMigrationDatabase;
import er.extensions.migration.ERXMigrationTable;
import er.extensions.migration.ERXModelVersion;

public class NotificationModel0 extends ERXMigrationDatabase.Migration {
	@Override
	public NSArray<ERXModelVersion> modelDependencies() {
		return null;
	}
  
	@Override
	public void downgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		// DO NOTHING
	}

	@Override
	public void upgrade(EOEditingContext editingContext, ERXMigrationDatabase database) throws Throwable {
		ERXMigrationTable deviceTable = database.newTableNamed("device");
		deviceTable.newIntegerColumn("id", false);
		deviceTable.newStringColumn("platform", 20, false);
		deviceTable.newStringColumn("user", 50, false);
		deviceTable.create();
	 	deviceTable.setPrimaryKey("id");

		ERXMigrationTable logTable = database.newTableNamed("log");
		logTable.newIntegerColumn("deviceID", false);
		logTable.newIntegerColumn("id", false);
		logTable.newStringColumn("message", 255, true);
		logTable.newIntegerColumn("sendingTime", false);
		logTable.create();
	 	logTable.setPrimaryKey("id");

		logTable.addForeignKey("deviceID", "device", "id");
	}
}
