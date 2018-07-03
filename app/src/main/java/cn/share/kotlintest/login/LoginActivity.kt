package cn.share.kotlintest.login

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import cn.share.kotlintest.FRString
import cn.share.kotlintest.MainActivity
import cn.share.kotlintest.R
import cn.share.kotlintest.dialog.FRDialog
import cn.share.kotlintest.dialog.FRDialogClickListener
import cn.share.kotlintest.dp
import cn.share.kotlintest.extendfunction.onClickDebounce
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.experimental.delay

/**
 * Created by jack on 2018/5/19
 */

class LoginActivity : Activity() {

    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var phoneInput: LoginInputUIComponent
    private lateinit var passwordInput: LoginInputUIComponent

    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        compositeDisposable = CompositeDisposable()

        phoneInput = al_phone_input as LoginInputUIComponent
        passwordInput = al_password_input as LoginInputUIComponent

        phoneInput.initDelete()
        phoneInput.setInputType(InputType.TYPE_CLASS_PHONE)
        passwordInput.initDelete()
        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)

        phoneInput.setInput("1776457")
        passwordInput.setInput("123456")

        compositeDisposable.add(Observable.combineLatest(phoneInput.inputObservable, passwordInput.inputObservable
                , BiFunction<CharSequence, CharSequence, Boolean> { phoneCharacter, passwordCharacter ->
            !FRString.isNullable(phoneCharacter.toString(), passwordCharacter.toString())
        }).subscribe { aBoolean ->
            al_tv_login.isEnabled = aBoolean
        })

        al_tv_login.onClickDebounce {
            showDialog()
            delay(300)
        }

        val view = View(this)
        view.layoutParams = ViewGroup.LayoutParams(100.dp(), 20.dp())
        view.setBackgroundColor(Color.GREEN)
        al_ll_test.addView(view)
    }

    private fun showDialog() {
        FRDialog.MaterialDesignBuilder(this)
                .setTitle("温馨提示")
                .setMessage("您即将要登录，是否继续？")
                .setNegativeContentAndListener("否", null)
                .setPositiveContentAndListener("是", object : FRDialogClickListener {
                    override fun onDialogClick(view: View): Boolean {
                        MainActivity.start(this@LoginActivity)
                        return true
                    }
                }).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        phoneInput.onDestroy()
        passwordInput.onDestroy()
        compositeDisposable.dispose()
    }
}