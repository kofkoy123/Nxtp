package com.lzr.menu.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.lzr.base.common.BaseConstant
import com.lzr.base.ui.adapter.BaseRecyclerViewAdapter
import com.lzr.base.ui.fragment.BaseFragment
import com.lzr.base.widgets.BannerImageLoader
import com.lzr.menu.R
import com.lzr.menu.adapter.MenuAdapter
import com.lzr.menu.bean.MenuType
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_menu.*
import org.jetbrains.anko.support.v4.toast

/**
 * 作者： 10302
 * 创建时间：2019/7/8
 * 作用：菜单fragment
 */
class MenuFragment : BaseFragment() {


    private lateinit var mMenuAdapter: MenuAdapter
    private val mTypes: MutableList<MenuType> = mutableListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_menu, null)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        setTypeListAdapter()

        setDetailListAdapter()
    }


    private fun initBanner() {
        mBanner.setImageLoader(BannerImageLoader())
        mBanner.setImages(
            listOf(
                BaseConstant.HOME_BANNER_ONE, BaseConstant.HOME_BANNER_TWO,
                BaseConstant.HOME_BANNER_THREE, BaseConstant.HOME_BANNER_FOUR
            )
        )
        mBanner.setDelayTime(4000)
        mBanner.setBannerAnimation(Transformer.Accordion)
        mBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mBanner.start()
    }


    private fun setTypeListAdapter() {

        mTypes.add(MenuType("食品",true))
        mTypes.add(MenuType("蛋糕",false))
        mTypes.add(MenuType("奶茶",false))
        mTypes.add(MenuType("食品",false))
        mTypes.add(MenuType("食品",false))

        mGoodsTypeList.layoutManager = LinearLayoutManager(context)

        mMenuAdapter = MenuAdapter(context!!)
        mMenuAdapter.setData(mTypes)
        mGoodsTypeList.adapter = mMenuAdapter
        mMenuAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<MenuType> {

            override fun onItemClick(item: MenuType, position: Int) {
                toast(item.name)
            }
        })
    }

    private fun setDetailListAdapter() {


    }


}