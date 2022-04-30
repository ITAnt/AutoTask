package com.itant.at.db

import com.miekir.common.context.GlobalContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * 数据库管理者
 */
object DbManager {
    /**
     * 保证数据安全插入数据库
     */
    val mService: ExecutorService = Executors.newSingleThreadExecutor()

    /**
     * 数据库配置
     */
    val ormConfig = OrmConfig(GlobalContext.getContext())
}