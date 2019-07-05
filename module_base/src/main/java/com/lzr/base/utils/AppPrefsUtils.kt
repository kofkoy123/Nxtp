package com.lzr.base.utils

import android.content.Context
import android.content.SharedPreferences
import com.lzr.base.common.BaseApplication
import com.lzr.base.common.BaseConstant

/**
 * 作者： 10302
 * 创建时间：2019/7/4
 */
object AppPrefsUtils {

    private var mSp: SharedPreferences =
        BaseApplication.mContext.getSharedPreferences(BaseConstant.TABLE_PREFS, Context.MODE_PRIVATE)
    private var mEdt: SharedPreferences.Editor

    init {
        mEdt = mSp.edit()
    }

    /**
     * 存放布尔值
     */
    fun putBoonlean(key: String, value: Boolean) {
        mEdt.putBoolean(key, value)
        mEdt.commit()
    }

    /**
     * 获取布尔值，默认false
     */
    fun getBoonlean(key: String): Boolean {
        return mSp.getBoolean(key, false)
    }

    /**
     * 保存字符串
     */
    fun putString(key: String, value: String) {
        mEdt.putString(key, value)
        mEdt.commit()
    }

    /**
     * 获取字符串
     */
    fun getString(key: String): String? {
        return mSp.getString(key, "")
    }

    /**
     *  Int数据
     */
    fun putInt(key: String, value: Int) {
        mEdt.putInt(key, value)
        mEdt.commit()
    }

    /**
     * 默认 0
     */
    fun getInt(key: String): Int {
        return mSp.getInt(key, 0)
    }

    /*
        Long数据
     */
    fun putLong(key: String, value: Long) {
        mEdt.putLong(key, value)
        mEdt.commit()
    }

    /**
     *  默认 0
     */
    fun getLong(key: String): Long {
        return mSp.getLong(key, 0)
    }

    /**
     *   Set数据
     */
    fun putStringSet(key: String, set: Set<String>) {
        val localSet = getStringSet(key)?.toMutableSet()
        localSet?.addAll(set)
        mEdt.putStringSet(key, localSet)
        mEdt.commit()
    }

    /**
     *   默认空set
     */
    fun getStringSet(key: String): MutableSet<String>? {
        val set = setOf<String>()
        return mSp.getStringSet(key, set)
    }

    /**
     *    删除key数据
     */
    fun remove(key: String) {
        mEdt.remove(key)
        mEdt.commit()
    }
}