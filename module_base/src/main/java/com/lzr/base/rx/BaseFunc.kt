package com.lzr.base.rx


import com.lzr.base.common.ResultCode
import com.lzr.base.data.protocol.BaseResp
import io.reactivex.Observable
import io.reactivex.functions.Function

/**
 *   通用数据类型转换封装
 */
class BaseFunc<T> : Function<BaseResp<T>, Observable<T>> {
    override fun apply(t: BaseResp<T>): Observable<T> {
        if (t.status != ResultCode.SUCCESS) {
            return Observable.error(BaseException(t.status, t.message))
        }

        if (t.data == null){
            return Observable.error(DataNullException())
        }
        return Observable.just(t.data)
    }
}
