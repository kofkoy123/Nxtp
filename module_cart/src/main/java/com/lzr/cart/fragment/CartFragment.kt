package com.lzr.cart.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lzr.base.ui.fragment.BaseFragment
import com.lzr.cart.R

/**
 * 作者： 10302
 * 创建时间：2019/7/8
 * 作用：
 */
class CartFragment :BaseFragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_cart,null)
    }

}