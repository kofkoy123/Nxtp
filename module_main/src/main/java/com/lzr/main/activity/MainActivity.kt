package com.lzr.main.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.lzr.base.common.AppManager
import com.lzr.base.common.ArouterConstant
import com.lzr.main.R
import com.lzr.main.fragment.HomeFragment
import com.lzr.user.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

@Route(path = ArouterConstant.ACTIVITY_PATH_MAIN)
class MainActivity : AppCompatActivity() {

    @Autowired(name = ArouterConstant.KEY_USERNAME)
    lateinit var name: String

    private var pressTime: Long = 0

    private var mHomeFragment: HomeFragment = HomeFragment()
    private var mHomeFragment1: HomeFragment = HomeFragment()
    private var mHomeFragment2: HomeFragment = HomeFragment()
    private var mHomeFragment3: HomeFragment = HomeFragment()
    private var mMineFragment: MineFragment = MineFragment()

    private var mFragmentList = Stack<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.getInstance().inject(this)
        initFragment()
        initBottomNav()

        Log.e("lzr", "name==$name")

    }


    private fun initFragment() {
        var transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mContaier, mHomeFragment)
        transaction.add(R.id.mContaier, mHomeFragment1)
        transaction.add(R.id.mContaier, mHomeFragment2)
        transaction.add(R.id.mContaier, mHomeFragment3)
        transaction.add(R.id.mContaier, mMineFragment)
        transaction.commit()

        mFragmentList.add(mHomeFragment)
        mFragmentList.add(mHomeFragment1)
        mFragmentList.add(mHomeFragment2)
        mFragmentList.add(mHomeFragment3)
        mFragmentList.add(mMineFragment)

    }


    private fun initBottomNav() {
        mBottomNavBar.setTabSelectedListener(object : BottomNavigationBar.OnTabSelectedListener {
            override fun onTabReselected(position: Int) {
            }

            override fun onTabUnselected(position: Int) {
            }

            override fun onTabSelected(position: Int) {
                changeFragment(position)
            }
        })
        mBottomNavBar.checkMsgBadge(false)

    }

    private fun changeFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        for (fragment in mFragmentList) {
            transaction.hide(fragment)
        }
        transaction.show(mFragmentList[position])
        transaction.commit()
    }

    /**
     * 双击返回键退出APP
     */
    override fun onBackPressed() {
        super.onBackPressed()
        var currentTime = System.currentTimeMillis()
        if (currentTime - pressTime > 2000) {
            toast("再按一次退出APP")
            pressTime = currentTime
        } else {
            AppManager.instance.exitApp(this)
        }

    }
}
