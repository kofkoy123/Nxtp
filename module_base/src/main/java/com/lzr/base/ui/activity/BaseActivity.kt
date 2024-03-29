package com.lzr.base.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import com.lzr.base.common.AppManager
import com.lzr.base.utils.StatusBarUtil
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
import org.jetbrains.anko.find


/**
  *  Activity基类，业务无关
 */
open class BaseActivity: RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StatusBarUtil.setTranslucentForImageView(this@BaseActivity,0,null)

        AppManager.instance.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instance.finishActivity(this)
    }

    //获取Window中视图content
    val contentView: View
        get() {
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }


}