package com.lzr.main.activity

import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter
import com.lzr.base.common.ArouterConstant
import com.lzr.base.ui.activity.BaseActivity
import com.lzr.main.R

class WelcomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler().postDelayed(Runnable {
            ARouter.getInstance().build(ArouterConstant.ACTIVITY_PATH_LOGIN).navigation()
            finish()
        }, 1000)
    }


}
