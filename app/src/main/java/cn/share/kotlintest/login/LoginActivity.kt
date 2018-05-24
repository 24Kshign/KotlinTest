package cn.share.kotlintest.login

import android.app.Activity
import android.os.Bundle
import android.text.InputType
import android.view.View
import cn.share.kotlintest.FRString
import cn.share.kotlintest.MainActivity
import cn.share.kotlintest.R
import cn.share.kotlintest.dialog.FRDialog
import cn.share.kotlintest.dialog.FRDialogClickListener
import cn.share.kotlintest.util.FRToast
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Created by jack on 2018/5/19
 */

class LoginActivity : Activity() {

    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var phoneInput: LoginInputUIComponent
    private lateinit var passwordInput: LoginInputUIComponent

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

        compositeDisposable.add(Observable.combineLatest(phoneInput.inputObservable, passwordInput.inputObservable
                , BiFunction<CharSequence, CharSequence, Boolean> { phoneCharacter, passwordCharacter ->
            !FRString.isNullable(phoneCharacter.toString(), passwordCharacter.toString())
        }).subscribe { aBoolean ->
            al_tv_login.isEnabled = aBoolean
        })

        al_tv_login.setOnClickListener {
            FRDialog.CommonBuilder(this)
                    .setContentView(R.layout.dialog_login)
                    .setCancelableOutside(false)
                    .setOnClickListener(R.id.dl_tv_login, object : FRDialogClickListener {
                        override fun onDialogClick(view: View): Boolean {
                            MainActivity.start(this@LoginActivity)
                            return true
                        }
                    }).setOnClickListener(R.id.dl_tv_register, object : FRDialogClickListener {
                        override fun onDialogClick(view: View): Boolean {
                            FRToast.showToast("暂不支持注册")
                            return false
                        }
                    })
                    .show()
        }
    }
}