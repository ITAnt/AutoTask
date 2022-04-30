package com.itant.at.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.ScreenUtils
import com.itant.mvp.kt.extension.enableHighRefreshRate
import com.miekir.mvp.view.binding.adapt.BindingActivity

/**
 * @date 2022-1-16 20:52
 * @author 詹子聪
 */
abstract class BaseActivity<VB : ViewBinding> : BindingActivity<VB>() {
    companion object {
        const val SIZE_IN_DP_WIDTH = 375.0f
    }

    override fun isBaseOnWidth(): Boolean {
        return ScreenUtils.getScreenWidth() < ScreenUtils.getAppScreenHeight()
    }

    override fun getSizeInDp(): Float {
        return SIZE_IN_DP_WIDTH
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        touchSpaceHideKeyboard = true
        // 启用高刷新率
        enableHighRefreshRate()
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        // 必须要在onPause隐藏键盘，在onDestroy就太晚了
        KeyboardUtils.hideSoftInput(this)
        super.onPause()
    }
}