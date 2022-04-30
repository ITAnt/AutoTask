package com.itant.at.ui.main

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.itant.at.R
import com.itant.at.base.BaseActivity
import com.itant.at.bean.TaskBean
import com.itant.mvp.kt.extension.setSingleClick
import com.miekir.common.autosize.AutoSizeCompat

/**
 * 首页适配器
 */
class MainAdapter(mList: MutableList<TaskBean>): BaseQuickAdapter<TaskBean, BaseViewHolder>(
    R.layout.adapter_main, data = mList) {

    override fun convert(holder: BaseViewHolder, item: TaskBean) {
        val tv_name = holder.getView<TextView>(R.id.tv_name)
        tv_name.text = item.taskName

        val btn_run = holder.getView<View>(R.id.btn_run)
        btn_run.setSingleClick {
            // 运行
            (context as? MainActivity)?.presenter?.runTask(item)
        }

        val btn_delete = holder.getView<View>(R.id.btn_delete)
        btn_delete.setOnClickListener {
            // 删除
            AutoSizeCompat.autoConvertDensity(context.resources, BaseActivity.SIZE_IN_DP_WIDTH, ScreenUtils.getScreenWidth() < ScreenUtils.getScreenHeight())
            AlertDialog.Builder(context, R.style.AdaptAlertDialog)
                .setMessage("确定删除${item.taskName}？")
                .setPositiveButton("确定", DialogInterface.OnClickListener() { dialog, which ->
                    (context as? MainActivity)?.presenter?.deleteTask(item, holder.absoluteAdapterPosition)
                    dialog.dismiss()
                })
                .setNegativeButton("取消", DialogInterface.OnClickListener() { dialog, which ->
                    dialog.dismiss()
                }).show()
        }
    }
}