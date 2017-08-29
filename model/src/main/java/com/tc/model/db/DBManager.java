package com.tc.model.db;

import android.content.Context;

import com.tc.model.db.greendao.DaoMaster;
import com.tc.model.db.greendao.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by itcayman on 2017/8/28.
 */

public class DBManager {
    private static DBManager dbManager;
    private static final String DB_NAME = "audio_player";
    private DaoSession daoSession;

    private DBManager(Context context) {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        Database database = openHelper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    public static DBManager getInstance(Context context) {
        if (dbManager == null) {
            dbManager = new DBManager(context);
        }
        return dbManager;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
