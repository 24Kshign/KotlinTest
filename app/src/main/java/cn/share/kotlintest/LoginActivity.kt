package cn.share.kotlintest

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import cn.share.kotlintest.FRString.isNullable

/**
 * Created by jack on 2018/5/19
 */

class LoginActivity : Activity() {

    private val string=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Toast.makeText(this, string.isNullable().toString(), Toast.LENGTH_SHORT).show()
    }

}