package com.itant.at.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.itant.at.bean.TaskBean;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * 功能：数据库配置
 * 注意：修改字段，增加字段（或者bean的包名改动）需要升级数据库版本并执行相关字段修正（如删除表重新创建表）
 */
public class OrmConfig extends OrmLiteSqliteOpenHelper {
    /**
     * 数据库名字
     */
    private static final String DB_NAME = "auto_task.db";

    /**
     * 数据库版本
     */
    private static final int DB_VERSION = 1;

    /**
     * 构造方法
     * @param context 上下文
     */
    public OrmConfig(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 这里创建表
     */
    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        // 创建表
        try {
            TableUtils.createTableIfNotExists(connectionSource, TaskBean.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这里进行更新表操作
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, TaskBean.class, true);
            onCreate(sqLiteDatabase, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
    }
}
