package com.itant.at.ui.main.add

import android.app.Activity.RESULT_OK
import com.blankj.utilcode.util.ToastUtils
import com.itant.at.bean.ActionBean
import com.itant.at.bean.SerializedList
import com.itant.at.bean.TaskBean
import com.itant.at.db.TaskManager
import com.itant.mvp.kt.extension.launchTask
import com.miekir.common.widget.loading.LoadingType
import com.miekir.mvp.presenter.BasePresenter

/**
 * @date 2022-4-30 17:43
 * @author 詹子聪
 */
class AddPresenter: BasePresenter<AddActivity>() {

    /**
     * 添加任务到数据库
     */
    fun addTask(name: String, rawActionList: ArrayList<ActionBean>) {
        launchTask(
            {
                val list = SerializedList<ActionBean>()
                list.addAll(rawActionList)
                val taskBean = TaskBean().apply {
                    taskName = name
                    actionList = list
                }
                TaskManager.addOrUpdateTask(taskBean)
            }, onSuccess = {
                view?.run {
                    setResult(RESULT_OK)
                    finish()
                }
            }, loadingType = LoadingType.STICKY, onError = {_, message ->
                ToastUtils.showLong(message)
            }
        )
    }
}