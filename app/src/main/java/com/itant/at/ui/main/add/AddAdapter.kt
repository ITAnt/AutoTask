package com.itant.at.ui.main.add

import android.view.View
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.itant.at.R
import com.itant.at.bean.ActionBean
import com.itant.at.bean.ActionType

/**
 * 首页适配器
 */
class AddAdapter(val actionList: MutableList<ActionBean>): BaseQuickAdapter<ActionBean, BaseViewHolder>(
    R.layout.adapter_add, data = actionList) {

    override fun convert(holder: BaseViewHolder, item: ActionBean) {
        val tv_content = holder.getView<TextView>(R.id.tv_content)
        tv_content.text = ActionType.values()[item.type].typeName

        // 点击移除
        val btn_remove = holder.getView<View>(R.id.btn_remove)
        if (item.canBeRemove) {
            btn_remove.visibility = View.VISIBLE
            btn_remove.setOnClickListener {
                val position = holder.absoluteAdapterPosition
                actionList.remove(item)
                notifyItemRemoved(position)
            }
        } else {
            btn_remove.visibility = View.GONE
        }
    }
}