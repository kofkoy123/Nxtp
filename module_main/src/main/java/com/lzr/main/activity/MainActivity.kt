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
import com.lzr.cart.fragment.CartFragment
import com.lzr.main.R
import com.lzr.main.fragment.HomeFragment
import com.lzr.menu.fragment.MenuFragment
import com.lzr.order.fragment.OrderFragment
import com.lzr.user.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import java.util.*

@Route(path = ArouterConstant.ACTIVITY_PATH_MAIN)
class MainActivity : AppCompatActivity() {

    @Autowired(name = ArouterConstant.KEY_USERNAME)
    lateinit var name: String

    private var pressTime: Long = 0

    private val mHomeFragment: HomeFragment = HomeFragment()
    private val mMenuFragment: MenuFragment = MenuFragment()
    private val mOrderFragment: OrderFragment = OrderFragment()
    private val mCartFragment: CartFragment = CartFragment()
    private val mMineFragment: MineFragment = MineFragment()

    private val mFragmentList = Stack<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.getInstance().inject(this)
        initFragment()
        initBottomNav()

        Log.e("lzr", "name==$name")

        var b: String? = null
        Log.e("lzr", "b的值==${b?.length}")
    }


    private fun initFragment() {
        mFragmentList.add(mHomeFragment)
        mFragmentList.add(mMenuFragment)
        mFragmentList.add(mOrderFragment)
        mFragmentList.add(mCartFragment)
        mFragmentList.add(mMineFragment)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.mContainer, mHomeFragment)
        transaction.add(R.id.mContainer, mMenuFragment)
        transaction.add(R.id.mContainer, mOrderFragment)
        transaction.add(R.id.mContainer, mCartFragment)
        transaction.add(R.id.mContainer, mMineFragment)

        for (fragment in mFragmentList) {
            transaction.hide(fragment)
        }
        transaction.show(mHomeFragment)
        transaction.commit()
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
        mBottomNavBar.checkCartBadge(0)
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
        var currentTime = System.currentTimeMillis()
        if (currentTime - pressTime > 2000) {
            toast("再按一次退出APP")
            pressTime = currentTime
        } else {
            super.onBackPressed()
            AppManager.instance.exitApp(this)
        }

    }

}
