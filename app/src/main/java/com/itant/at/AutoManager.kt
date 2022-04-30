package com.itant.at

import android.accessibilityservice.GestureDescription
import android.accessibilityservice.GestureDescription.StrokeDescription
import android.graphics.Path
import com.blankj.utilcode.util.ActivityUtils
import com.itant.at.service.ActionService
import com.miekir.common.context.GlobalContext


/**
 * @date 2022-4-30 11:13
 * @author 詹子聪
 */
object AutoManager {
    private const val TIME_START = 100L
    private const val TIME_DURATION = 50L

    var service : ActionService? = null
    private val displayMetrics = GlobalContext.getContext().resources.displayMetrics
    private val xMiddle = displayMetrics.widthPixels / 2.0f
    private val yMiddle = displayMetrics.heightPixels / 2.0f

    private val xLeft = xMiddle / 2
    private val xRight = xLeft * 3

    private val yTop = yMiddle / 2
    private val yBottom = yTop * 3

    /**
     * 从左往右滑动
     */
    fun swipeLeft2Right() {
        val path = Path().apply {
            moveTo(xLeft, yMiddle)
            lineTo(xRight, yMiddle)
        }
        dispatchSwipeGesture(path)
    }

    /**
     * 从右往左滑动
     */
    fun swipeRight2Left() {
        val path = Path().apply {
            moveTo(xRight, yMiddle)
            lineTo(xLeft, yMiddle)
        }
        dispatchSwipeGesture(path)
    }

    /**
     * 从上往下滑动
     */
    fun swipeTop2Bottom() {
        val path = Path().apply {
            moveTo(xMiddle, yTop)
            lineTo(xMiddle, yBottom)
        }
        dispatchSwipeGesture(path)
    }

    /**
     * 从下往上滑动
     */
    fun swipeBottom2Top() {
        val path = Path().apply {
            moveTo(xMiddle, yBottom)
            lineTo(xMiddle, yTop)
        }
        dispatchSwipeGesture(path)
    }

    /**
     * 回到桌面
     */
    fun clickHome() {
        ActivityUtils.startHomeActivity()
    }

    /**
     * 模拟手势滑动
     */
    private fun dispatchSwipeGesture(path: Path) {
        val stroke = GestureDescription.StrokeDescription(path, TIME_START, TIME_DURATION)
        service?.dispatchGesture(
            GestureDescription.Builder().addStroke(stroke).build(), null, null
        )
    }

    /**
     * 点击事件
     * 这里的坐标轴可以通过打开开发者选项里的“指针位置（屏幕叠加层显示当前触摸数据）”来获取，这种通过点击指定像素位置的
     * 实现无法做到兼容所有设备，如果要点击特定按钮，可以通过在service里查找控件并触发点击
     */
    fun performClick(x: Float, y: Float) {
        val clickPath = Path()
        clickPath.moveTo(x, y)
        val clickStroke = StrokeDescription(clickPath, TIME_START, TIME_DURATION)
        val gestureDescription = GestureDescription.Builder().addStroke(clickStroke).build()
        service?.dispatchGesture(gestureDescription, null, null)
    }
}