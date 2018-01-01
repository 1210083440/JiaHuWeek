package com.bw.jiahuweek1.application;

import android.app.Application;

import com.bw.jiahuweek1.bean.DaoMaster;
import com.bw.jiahuweek1.bean.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by 小小云 on 2017/12/30.
 */

public class MyApplication extends Application {

    private static DaoSession sDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        initGreenDAO();
    }

    private void initGreenDAO() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "dish.db");
        Database db = helper.getWritableDb();
        // 数据库中开启会话，在会话中操作数据库
         sDaoSession = new DaoMaster(db).newSession();
    }

    public static DaoSession getsDaoSession() {
        return sDaoSession;
    }
}
