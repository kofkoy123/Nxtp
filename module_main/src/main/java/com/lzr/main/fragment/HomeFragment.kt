package com.lzr.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lzr.base.common.BaseConstant
import com.lzr.base.ui.fragment.BaseFragment
import com.lzr.base.widgets.BannerImageLoader
import com.lzr.main.R
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * 作者： 10302
 * 创建时间：2019/7/5
 * 作用：首页界面
 */
class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, null)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBanner()

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


}