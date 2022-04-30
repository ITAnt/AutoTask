package com.itant.at.bean

import androidx.annotation.Keep
import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * @date 2022-4-30 16:21
 * @author 詹子聪
 */
@Keep
@DatabaseTable
class TaskBean {

    @DatabaseField(generatedId = true)
    var taskId: Int = 0

    @DatabaseField
    var taskName: String = ""

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    var actionList : SerializedList<ActionBean>? = null
}