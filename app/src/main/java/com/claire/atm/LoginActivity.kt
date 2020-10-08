package com.claire.atm

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //2.填入已儲存的帳號
        val userid:String? = getSharedPreferences("atm", Context.MODE_PRIVATE)
            .getString("PREF_USERID","")
        ed_userid.setText(userid)
    }

    fun login(view: View) {
        val userid = ed_userid.text.toString()
        val passwd = ed_passwd.text.toString()
        
        if (userid == "claire" && passwd == "1234"){
            //1.儲存登入帳號
            getSharedPreferences("atm", Context.MODE_PRIVATE)
                .edit()
                .putString("PREF_USERID", userid)
                .apply()

            Toast.makeText(this, "登入成功", Toast.LENGTH_SHORT).show()

            //將帳號與密碼放入 Intent 物件中，再呼叫 Activity 的 setResult 方法，
            intent.putExtra("LOGIN_USERID", userid)
            intent.putExtra("LOGIN_PASSWD", passwd)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else{
            AlertDialog.Builder(this)
                .setTitle("Atm")
                .setMessage("登入失敗")
                .setPositiveButton("ok", null)
                .show()
        }

    }

    fun cancel(view: View) {

    }

}