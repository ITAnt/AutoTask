package com.itant.at.ext

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.text.TextUtils
import android.text.TextUtils.SimpleStringSplitter
import android.widget.TextView
import android.widget.Toast
import com.blankj.utilcode.util.ScreenUtils
import com.itant.at.base.BaseActivity.Companion.SIZE_IN_DP_WIDTH
import com.itant.at.service.ActionService
import com.miekir.common.autosize.AutoSizeCompat
import com.miekir.common.log.L
import com.miekir.common.tools.ToastTools


/**
 * 获取文本
 */
fun TextView.getString(message: String? = null): String? {
    val text = this.text.toString()
    if (TextUtils.isEmpty(text)) {
        if (!TextUtils.isEmpty(message)) {
            ToastTools.showShort(message)
        }
        return null
    }
    return text
}

/**
 * 适配对话框
 */
fun Dialog.showAdapt() {
    val width = if (ScreenUtils.getScreenWidth() < ScreenUtils.getScreenHeight()) {
        SIZE_IN_DP_WIDTH
    } else {
        SIZE_IN_DP_WIDTH * ScreenUtils.getScreenWidth() * 1.0f / ScreenUtils.getScreenHeight()
    }
    AutoSizeCompat.autoConvertDensity(context.resources, width, ScreenUtils.getScreenWidth() < ScreenUtils.getScreenHeight())
    show()
}

/**
 * 是否开启了辅助
 */
fun Context.isAccessibilityEnable(): Boolean {
    var accessibilityEnabled = 0
    val service = "${packageName}/${ActionService::class.java.canonicalName}"
    try {
        accessibilityEnabled = Settings.Secure.getInt(applicationContext.contentResolver, Settings.Secure.ACCESSIBILITY_ENABLED)
        // Log.v(TAG, "accessibilityEnabled = " + accessibilityEnabled);
    } catch (e: SettingNotFoundException) {
        // Log.e(TAG, "Error finding setting, default accessibility to not found: " + e.getMessage());
    }
    val mStringColonSplitter = SimpleStringSplitter(':')
    if (accessibilityEnabled == 1) {
        val settingValue = Settings.Secure.getString(applicationContext.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        if (settingValue != null) {
            mStringColonSplitter.setString(settingValue)
            while (mStringColonSplitter.hasNext()) {
                val accessibilityService = mStringColonSplitter.next()
                if (accessibilityService.equals(service, ignoreCase = true)) {
                    return true
                }
            }
        }
    }
    Toast.makeText(this, "请开启自动任务的辅助服务", Toast.LENGTH_LONG).show()
    val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    try {
        startActivity(intent)
    } catch (e: Exception) {
        L.e(e.message)
    }
    return false
    //val isExploreByTouchEnabled = am?.isTouchExplorationEnabled
}