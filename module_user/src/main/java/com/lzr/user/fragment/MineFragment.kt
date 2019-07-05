package com.lzr.user.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lzr.base.ui.fragment.BaseFragment
import com.lzr.user.R

/**
 * 作者： 10302
 * 创建时间：2019/7/5
 * 作用：我的界面
 */
class MineFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_mine, null)
    }

}