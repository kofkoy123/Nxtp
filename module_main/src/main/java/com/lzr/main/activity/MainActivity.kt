package com.lzr.main.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lzr.base.common.AppManager
import com.lzr.base.common.ArouterConstant
import com.lzr.base.ui.activity.BaseActivity
import com.lzr.cart.fragment.CartFragment
import com.lzr.main.R
import com.lzr.main.fragment.HomeFragment
import com.lzr.menu.fragment.MenuFragment
import com.lzr.order.fragment.OrderFragment
import com.lzr.user.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

@Route(path = ArouterConstant.ACTIVITY_PATH_MAIN)
class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener {


    @Autowired(name = ArouterConstant.KEY_USERNAME)
    lateinit var name: String

    private var pressTime: Long = 0

    private val mHomeFragment: HomeFragment = HomeFragment()
    private val mMenuFragment: MenuFragment = MenuFragment()
    private val mOrderFragment: OrderFragment = OrderFragment()
    private val mCartFragment: CartFragment = CartFragment()
    private val mMineFragment: MineFragment = MineFragment()

    private val mFragmentList = mutableListOf<Fragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ARouter.getInstance().inject(this)
        initFragment()
        initBottomNav()
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
        mBottomNavBar.setIconSize(20f,20f)
        mBottomNavBar.setTextSize(14f)
        mBottomNavBar.selectedItemId = 0
        //监听切换事件
        mBottomNavBar.onNavigationItemSelectedListener = this

        mBottomNavBar.enableAnimation(false)
        mBottomNavBar.enableShiftingMode(false)
        mBottomNavBar.enableItemShiftingMode(false)

        // 平均布局
//        setItemType()
        //添加角标消息数
//        setAddNumber()
    }


//    private fun setAddNumber() {
//        //获取整个的NavigationView
//        var menuView = mBottomNavBar.getChildAt(0) as BottomNavigationMenuView
//        //获取所添加的每一个Tab，并给第三个Tab添加消息角标
//        var tabView = menuView.getChildAt(2)
//        var itemView = tabView as BottomNavigationItemView
//        //加载我们的角标布局View，新创建的一个布局
//        var badgeView = LayoutInflater.from(this).inflate(R.layout.menu_badge, menuView, false)
//        var number = badgeView.findViewById(R.id.tv_msg_count)
//        //设置显示的内容
//        number.setText("99")
//        //添加到Tab上
//        itemView.addView(badgeView)
//    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                changeFragment(0)
                return true
            }
            R.id.type -> {
                changeFragment(1)
                return true
            }
            R.id.message -> {
                changeFragment(2)
                return true
            }
            R.id.cart -> {
                changeFragment(3)
                return true
            }
            R.id.mine -> {
                changeFragment(4)
                return true
            }
        }
        return false
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
