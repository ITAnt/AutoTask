package com.itant.at.bean

import androidx.annotation.Keep
import java.io.Serializable

/**
 * 动作类型
 */
@Keep
enum class ActionType(val typeName: String) {
    /**
     * 点击
     */
    CLICK("点击"),

    /**
     * 点击home键
     */
    CLICK_HOME("回桌面"),

    /**
     * 长按
     */
    LONG_CLICK("长按"),

    /**
     * 左滑，向左翻页
     */
    SWIPE_LEFT("从左往右滑"),

    /**
     * 右滑，向右翻页
     */
    SWIPE_RIGHT("从右往左滑"),

    /**
     * 向上翻页
     */
    SWIPE_UP("从上往下滑"),

    /**
     * 向下翻页
     */
    SWIPE_DOWN("从下往上滑")
}

/**
 * 动作实体
 * @date 2022-4-30 10:27
 * @author 詹子聪
 */
@Keep
class ActionBean: Serializable {
    var startX: Float = 0.0f

    var startY: Float = 0.0f

    var type: Int = ActionType.CLICK.ordinal

    var endX: Float = 0.0f

    var endY: Float = 0.0f

    /**
     * 是否可以移除
     */
    var canBeRemove: Boolean = true
}