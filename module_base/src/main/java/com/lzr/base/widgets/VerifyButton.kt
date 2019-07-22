package com.lzr.base.widgets

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.widget.Button
import androidx.core.content.ContextCompat

import com.lzr.base.R

/*
    获取验证码按钮
    带倒计时
 */
class VerifyButton(context: Context, attrs: AttributeSet) : Button(context, attrs) {
    private val mHandler: Handler
    private var mCount = 60
    private var mOnVerifyBtnClick: OnVerifyBtnClick? = null
    private val mContext: Context

    init {
        this.text = "获取验证码"
        mHandler = Handler()
        this.mContext = context
    }

    /*
        倒计时，并处理点击事件
     */
    fun requestSendVerifyNumber() {
        mHandler.postDelayed(countDown, 0)
        if (mOnVerifyBtnClick != null) {
            mOnVerifyBtnClick!!.onClick()
        }
    }

    /*
        倒计时
     */
    private val countDown = object : Runnable {
        override fun run() {
            this@VerifyButton.text = mCount.toString() + "s "
            this@VerifyButton.setBackgroundColor(ContextCompat.getColor(mContext, R.color.common_disable))
            this@VerifyButton.setTextColor(ContextCompat.getColor(mContext, R.color.common_white))
            this@VerifyButton.isEnabled = false

            if (mCount > 0) {
                mHandler.postDelayed(this, 1000)
            } else {
                resetCounter()
            }
            mCount--
        }
    }

    fun removeRunable() {
        mHandler.removeCallbacks(countDown)
    }

    /*
        恢复到初始状态
     */
    fun resetCounter(vararg text: String) {
        this.isEnabled = true
        if (text.isNotEmpty() && "" != text[0]) {
            this.text = text[0]
        } else {
            this.text = "重获验证码"
        }
        this.setBackgroundColor(ContextCompat.getColor(mContext, R.color.transparent))
        this.setTextColor(ContextCompat.getColor(mContext, R.color.common_blue))
        mCount = 60
    }

    /*
        点击事件接口
     */
    interface OnVerifyBtnClick {
        fun onClick()
    }

    fun setOnVerifyBtnClick(onVerifyBtnClick: OnVerifyBtnClick) {
        this.mOnVerifyBtnClick = onVerifyBtnClick
    }
}
