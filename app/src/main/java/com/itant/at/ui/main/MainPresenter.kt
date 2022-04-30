package com.itant.at.ui.main

import com.blankj.utilcode.util.ToastUtils
import com.itant.at.AutoManager
import com.itant.at.bean.ActionBean
import com.itant.at.bean.ActionType
import com.itant.at.bean.TaskBean
import com.itant.at.db.TaskManager
import com.itant.mvp.kt.extension.launchTask
import com.miekir.common.widget.loading.LoadingType
import com.miekir.mvp.presenter.BasePresenter
import kotlinx.coroutines.delay

/**
 * @date 2022-4-30 18:40
 * @author 詹子聪
 */
class MainPresenter: BasePresenter<MainActivity>() {
    private var isRunning = false

    /**
     * 删除任务
     */
    fun deleteTask(taskBean: TaskBean, position: Int) {
        launchTask(
            {
                TaskManager.deleteTask(taskBean)
            }, onSuccess = {
                view?.run {
                    mList.remove(taskBean)
                    mAdapter.notifyItemRemoved(position)
                }
            }
        )
    }

    fun runTask(taskBean: TaskBean) {
        if (isRunning) {
            ToastUtils.showShort("请等待${taskBean.taskName}完成")
            return
        }
        isRunning = true

        launchTask(
            {
                val actionList = taskBean.actionList
                if (actionList != null) {
                    for (action in actionList) {
                        //CoroutineScope(Dispatchers.Main).launch {}
                        performAction(action)
                        // 延迟5秒才执行，是为了可能由于网络等原因，手机页面加载比较慢（尤其是WebView）
                        delay(5_000L)
                    }
                }
            }, onResult = { success, result, code, message ->
                isRunning = false
                ToastUtils.showShort("${taskBean.taskName}已完成")
            }, loadingType = LoadingType.INVISIBLE
        )
    }

    /**
     * 执行动作
     */
    private fun performAction(action: ActionBean) {
        when (ActionType.values()[action.type]) {
            ActionType.CLICK -> {
                AutoManager.performClick(action.startX, action.startY)
            }
            ActionType.SWIPE_LEFT -> {
                AutoManager.swipeLeft2Right()
            }
            ActionType.SWIPE_RIGHT -> {
                AutoManager.swipeRight2Left()
            }
            ActionType.SWIPE_UP -> {
                AutoManager.swipeTop2Bottom()
            }
            ActionType.SWIPE_DOWN -> {
                AutoManager.swipeBottom2Top()
            }
            ActionType.CLICK_HOME -> {
                AutoManager.clickHome()
            }
            else -> {}
        }
    }

    /**
     * 获取所有任务
     */
    fun getAllTask() {
        launchTask(
            {
                TaskManager.getAllTask()
            }, onSuccess = {
                if (it != null) {
                    view?.run {
                        mList.clear()
                        mList.addAll(it)
                        mAdapter.notifyDataSetChanged()
                    }
                }
            }
        )
    }
}