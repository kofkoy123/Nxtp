package com.lzr.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.scwang.smartrefresh.layout.SmartRefreshLayout

/**
 * 作者： 10302
 * 创建时间：2019/7/10
 * 作用：回弹效果的scrollview
 */
class ReboundScrollView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SmartRefreshLayout(context, attrs, defStyleAttr) {

    init {
        this@ReboundScrollView.setEnableRefresh(false)
        this@ReboundScrollView.setEnableLoadMore(false)
        this@ReboundScrollView.setEnableOverScrollDrag(true)
        this@ReboundScrollView.setReboundDuration(500)
    }

}