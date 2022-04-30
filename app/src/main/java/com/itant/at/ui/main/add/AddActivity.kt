package com.itant.at.ui.main.add

import androidx.recyclerview.widget.LinearLayoutManager
import com.itant.at.base.BaseActivity
import com.itant.at.bean.ActionBean
import com.itant.at.bean.ActionType
import com.itant.at.databinding.ActivityAddBinding
import com.itant.at.ext.getString
import com.itant.mvp.kt.extension.lazy
import com.itant.mvp.kt.extension.setSingleClick

/**
 * 添加动作的界面
 * @date 2022-4-30 13:49
 * @author 詹子聪
 */
class AddActivity: BaseActivity<ActivityAddBinding>() {
    private val mList = ArrayList<ActionBean>().apply {
        add(ActionBean().apply {
            type = ActionType.CLICK_HOME.ordinal
            canBeRemove = false
        })
    }
    private val mAdapter = AddAdapter(mList)

    private val presenter: AddPresenter by lazy()

    override fun onBindingInflate() = ActivityAddBinding.inflate(layoutInflater)

    override fun onInit() {
        binding.rvContent.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        // 添加点击动作
        binding.btnClick.setSingleClick {
            val x = binding.etX.getString("请输入X轴坐标") ?: return@setSingleClick
            val y = binding.etY.getString("请输入Y轴坐标") ?: return@setSingleClick
            mList.add(ActionBean().apply {
                type = ActionType.CLICK.ordinal
                startX = x.toFloat()
                startY = y.toFloat()
            })
            mAdapter.notifyItemInserted(mList.size-1)
        }

        // 添加回桌面动作
        binding.btnHome.setSingleClick {
            mList.add(ActionBean().apply { type = ActionType.CLICK_HOME.ordinal })
            mAdapter.notifyItemInserted(mList.size-1)
        }

        // 添加左往右滑动
        binding.btnSwipeLeft.setSingleClick {
            mList.add(ActionBean().apply { type = ActionType.SWIPE_LEFT.ordinal })
            mAdapter.notifyItemInserted(mList.size-1)
        }

        // 添加右往左滑动
        binding.btnSwipeRight.setSingleClick {
            mList.add(ActionBean().apply { type = ActionType.SWIPE_RIGHT.ordinal })
            mAdapter.notifyItemInserted(mList.size-1)
        }

        // 添加上往下滑动
        binding.btnSwipeUp.setSingleClick {
            mList.add(ActionBean().apply { type = ActionType.SWIPE_UP.ordinal })
            mAdapter.notifyItemInserted(mList.size-1)
        }

        // 添加下往上滑动
        binding.btnSwipeDown.setSingleClick {
            mList.add(ActionBean().apply { type = ActionType.SWIPE_DOWN.ordinal })
            mAdapter.notifyItemInserted(mList.size-1)
        }

        binding.btnFinish.setSingleClick {
            val taskName = binding.etName.getString("请输入任务名") ?: return@setSingleClick
            // 添加到数据库
            presenter.addTask(taskName, mList)
        }
    }
}