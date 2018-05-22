package cn.share.kotlintest.login

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import cn.share.kotlintest.FRString.isNullable
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.layout_login_input.view.*

/**
 * Created by jack on 2018/5/22
 */

class LoginInputUIComponent : RelativeLayout {

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    lateinit var inputObservable: Observable<CharSequence>
    lateinit var compositeDisposable: CompositeDisposable

    override fun onFinishInflate() {
        super.onFinishInflate()

        compositeDisposable = CompositeDisposable()
        inputObservable = RxTextView.textChanges(lli_et_input)
    }

    fun getInputContent(): String {
        return lli_et_input.text.toString()
    }

    fun initDelete() {
        compositeDisposable.add(inputObservable.subscribe { charSequence ->
            lli_tv_delete.visibility = if (charSequence.toString().isNullable()) View.GONE else View.VISIBLE
        })

        lli_tv_delete.setOnClickListener { lli_et_input.setText("") }
    }

    fun onDestroy() {
        compositeDisposable.dispose()
    }
}