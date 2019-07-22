package com.lzr.base.ext

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.AnimationDrawable
import android.os.Build
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.kennyc.view.MultiStateView
import com.lzr.base.R
import com.lzr.base.data.protocol.BaseResp
import com.lzr.base.rx.BaseFunc
import com.lzr.base.rx.BaseFuncBoolean
import com.lzr.base.rx.BaseSubscriber
import com.lzr.base.utils.GlideUtils
import com.lzr.base.widgets.DefaultTextWatcher
import com.trello.rxlifecycle3.LifecycleProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.find

//Kotlin通用扩展


/*
    扩展Observable执行
 */
fun <T> Observable<T>.execute(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(lifecycleProvider.bindToLifecycle())
            .subscribe(subscriber)
}

/*
    扩展数据转换
 */
fun <T> Observable<BaseResp<T>>.convert():Observable<T>{
    return this.flatMap(BaseFunc())
}

/*
    扩展Boolean类型数据转换
 */
fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean>{
    return this.flatMap(BaseFuncBoolean())
}

/*
    扩展点击事件
 */
fun View.onClick(listener:View.OnClickListener):View{
    setOnClickListener(listener)
    return this
}

/*
    扩展点击事件，参数为方法
 */
fun View.onClick(method:() -> Unit):View{
    setOnClickListener { method() }
    return this
}

/*
    扩展Button可用性
 */
fun Button.enable(et:EditText,method: () -> Boolean){
    val btn = this
    et.addTextChangedListener(object : DefaultTextWatcher(){
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            btn.isEnabled = method()
        }
    })
}

/*
    ImageView加载网络图片
 */
fun ImageView.loadUrl(url: String) {
    GlideUtils.loadUrlImage(context, url, this)
}

/*
    多状态视图开始加载
 */
fun MultiStateView.startLoading(){
    viewState = MultiStateView.VIEW_STATE_LOADING
    val loadingView = getView(MultiStateView.VIEW_STATE_LOADING)
    val animBackground = loadingView!!.find<View>(R.id.loading_anim_view).background
    (animBackground as AnimationDrawable).start()
}

/*
    扩展视图可见性
 */
fun View.setVisible(visible:Boolean){
    this.visibility = if (visible) View.VISIBLE else View.GONE
}


//----------尺寸转换----------

fun Context.dp2px(dpValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

fun Context.px2dp(pxValue: Float): Int {
    val scale = resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

fun Context.sp2px(spValue: Float): Int {
    val scale = resources.displayMetrics.scaledDensity
    return (spValue * scale + 0.5f).toInt()
}

fun Context.px2sp(pxValue: Float): Int {
    val scale = resources.displayMetrics.scaledDensity
    return (pxValue / scale + 0.5f).toInt()
}

//----------屏幕尺寸----------

fun Context.getScreenWidth(): Int {
    var wm: WindowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        ?: return resources.displayMetrics.widthPixels
    var point = Point()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        wm.defaultDisplay.getRealSize(point)
    } else {
        wm.defaultDisplay.getSize(point)
    }
    return point.x
}

fun Context.getScreenHeight(): Int {
    var wm: WindowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        ?: return resources.displayMetrics.heightPixels
    var point = Point()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        wm.defaultDisplay.getRealSize(point)
    } else {
        wm.defaultDisplay.getSize(point)
    }
    return point.y
}


fun Context.getColorExt(colorInt:Int):Int{
    return ContextCompat.getColor(this,colorInt)
}

