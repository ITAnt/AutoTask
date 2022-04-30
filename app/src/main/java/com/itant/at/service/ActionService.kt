package com.itant.at.service

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.itant.at.AutoManager

/**
 * 动作服务
 */
class ActionService : AccessibilityService() {
    override fun onCreate() {
        super.onCreate()
        AutoManager.service = this
    }

    override fun onDestroy() {
        super.onDestroy()
        AutoManager.service = null
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {}
    override fun onInterrupt() {}
}