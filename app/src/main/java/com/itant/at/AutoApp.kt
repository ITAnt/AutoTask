package com.itant.at

import android.app.Application
import com.blankj.utilcode.util.AppUtils
import com.miekir.mvp.view.anim.SlideAnimation
import com.miekir.task.MvpManager
import com.miekir.task.net.RetrofitManager
import com.readystatesoftware.chuck.ChuckInterceptor

/**
 * @date 2021-8-28 15:26
 * @author 詹子聪
 */
class AutoApp: Application() {
    override fun onCreate() {
        super.onCreate()

        // 初始化本地存储
        //val rootDir = MMKV.initialize(this)
        //L.i("mmkv root: $rootDir")

        // MVP相关设置
        MvpManager.getInstance().activityAnimation(SlideAnimation())
        RetrofitManager.getDefault()
            .addInterceptors(ChuckInterceptor(this))
            .printLog(AppUtils.isAppDebug())
    }
}