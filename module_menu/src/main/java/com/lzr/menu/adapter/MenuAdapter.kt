package com.lzr.menu.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lzr.base.ext.getColorExt
import com.lzr.base.ui.adapter.BaseRecyclerViewAdapter
import com.lzr.menu.R
import com.lzr.menu.bean.MenuType
import kotlinx.android.synthetic.main.item_type.view.*

/**
 * 作者： 10302
 * 创建时间：2019/7/8
 * 作用：菜单适配器
 */
class MenuAdapter(context: Context) : BaseRecyclerViewAdapter<MenuType, MenuAdapter.MenuHolder>(context) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.item_type, parent, false)
        return MenuHolder(view)
    }

    override fun onBindViewHolder(holder: MenuHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        var item = dataList[position]
        holder.itemView.mTypeNameTv.text = item.name
        if (item.isSelected) {
            holder.itemView.mRootLayout.setBackgroundColor(Color.WHITE)
            holder.itemView.mTypeSelView.visibility = View.VISIBLE
            holder.itemView.mTypeNameTv.setTextColor(mContext.getColorExt(R.color.text_light_dark))
        } else {
            holder.itemView.mRootLayout.setBackgroundColor(Color.TRANSPARENT)
            holder.itemView.mTypeSelView.visibility = View.INVISIBLE
            holder.itemView.mTypeNameTv.setTextColor(mContext.getColorExt(R.color.text_dark))
        }

    }

    class MenuHolder(view: View) : RecyclerView.ViewHolder(view)

}