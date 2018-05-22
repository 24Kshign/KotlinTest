package cn.share.kotlintest.login

import android.app.Activity
import android.os.Bundle
import cn.share.kotlintest.FRString
import cn.share.kotlintest.MainActivity
import cn.share.kotlintest.R
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
        passwordInput.initDelete()

        compositeDisposable.add(Observable.combineLatest(phoneInput.inputObservable, passwordInput.inputObservable
                , BiFunction<CharSequence, CharSequence, Boolean> { phoneCharacter, passwordCharacter ->
            FRString.isNullable(phoneCharacter.toString(), passwordCharacter.toString())
        }).subscribe { aBoolean ->
            al_tv_login.isEnabled = aBoolean
        })

        al_tv_login.setOnClickListener {
            MainActivity.start(this)
        }
    }

}