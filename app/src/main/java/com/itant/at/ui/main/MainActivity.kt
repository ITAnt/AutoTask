package com.itant.at.ui.main

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.itant.at.base.BaseActivity
import com.itant.at.bean.TaskBean
import com.itant.at.databinding.ActivityMainBinding
import com.itant.at.ext.isAccessibilityEnable
import com.itant.at.ui.main.add.AddActivity
import com.itant.mvp.kt.extension.lazy
import com.itant.mvp.kt.extension.openActivityForResult
import com.itant.mvp.kt.extension.setSingleClick
import com.miekir.mvp.view.result.ActivityResult

/**
 * 首页
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    val mList = ArrayList<TaskBean>()
    val mAdapter = MainAdapter(mList)

    val presenter: MainPresenter by lazy()

    override fun onBindingInflate() = ActivityMainBinding.inflate(layoutInflater)

    override fun onInit() {
        binding.rvContent.run {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
        }

        // 点击添加
        binding.viewAdd.setSingleClick {
            val addIntent = Intent(this@MainActivity, AddActivity::class.java)
            openActivityForResult(addIntent, object : ActivityResult() {
                override fun onResultOK(backIntent: Intent?) {
                    super.onResultOK(backIntent)
                    presenter.getAllTask()
                }
            })
        }

        presenter.getAllTask()
    }

    override fun onResume() {
        super.onResume()
        isAccessibilityEnable()
    }

    override fun onBackPressed() {}
}