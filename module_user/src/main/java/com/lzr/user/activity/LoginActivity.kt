package com.lzr.user.activity

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.lzr.base.common.ArouterConstant
import com.lzr.base.ext.onClick
import com.lzr.base.ui.activity.BaseActivity
import com.lzr.user.R
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = ArouterConstant.ACTIVITY_PATH_LOGIN)
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        mUserLoginBtn.onClick {
            ARouter.getInstance().build(ArouterConstant.ACTIVITY_PATH_MAIN)
                .withString(ArouterConstant.KEY_USERNAME, "小明")
                .navigation()
            finish()
        }
    }
}
