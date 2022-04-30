package com.itant.at.db

import com.itant.at.bean.TaskBean

/**
 * @date 2022-4-30 17:39
 * @author 詹子聪
 */
object TaskManager {
    /**
     * 任务实体-数据库
     */
    private val taskDao = DbManager.ormConfig.getDao(TaskBean::class.java)

    /**
     * 添加任务
     */
    fun addOrUpdateTask(taskBean: TaskBean) {
        taskDao.createIfNotExists(taskBean)
    }

    /**
     * 添加任务
     */
    fun deleteTask(taskBean: TaskBean) {
        taskDao.delete(taskBean)
    }

    /**
     * 获取所有的任务
     */
    fun getAllTask(): MutableList<TaskBean>? {
        return taskDao.queryForAll()
    }
}