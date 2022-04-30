package com.itant.at.base

import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.ScreenUtils
import com.miekir.mvp.view.binding.adapt.BindingFragment

/**
 * 基础Fragment
 * @date 2021-8-26 22:33
 * @author 詹子聪
 */
abstract class BaseFragment<VB : ViewBinding> : BindingFragment<VB>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getSizeInDp(): Float {
        return BaseActivity.SIZE_IN_DP_WIDTH
    }

    override fun isBaseOnWidth(): Boolean {
        return ScreenUtils.getScreenWidth() < ScreenUtils.getScreenHeight()
    }
}