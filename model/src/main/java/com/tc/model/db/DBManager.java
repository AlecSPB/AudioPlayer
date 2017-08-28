package com.tc.model.db;

/**
 * Created by itcayman on 2017/8/28.
 */

public class DBManager {
    private static DBManager dbManager;

    private DBManager() {
    }

    public DBManager getInstance() {
        if (dbManager == null) {
            dbManager = new DBManager();
        }
        return dbManager;
    }
}
