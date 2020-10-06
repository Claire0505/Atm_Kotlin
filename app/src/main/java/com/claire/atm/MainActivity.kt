package com.claire.atm

import android.app.Activity
import android.app.NativeActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    //定義功能常數
    companion object {
        val RC_LOGIN = 30
    }
    //一開始未登入狀態
    var login = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startActivity(Intent(this, MaterialActivity::class.java))

        //檢查使用者是否登入過
        if (!login){
            Intent(this, LoginActivity:: class.java).apply {
                //startActivityForResult 是到另一個畫面中並取得結果 ( 資料 ) 的
                startActivityForResult(this, RC_LOGIN)
            }

        }
    }

    //傳回LoginActivity Intent過來的資料
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //先判斷是否是 requestCode 是否為 RC_LOGIN，
        // 是的話再 進一步判斷 resultCode 是否為 RESULT_OK，最後再取得 Intent 中的資料
        if (requestCode == RC_LOGIN){
            if (resultCode == Activity.RESULT_OK){
                val userid = data?.getStringExtra("LOGIN_USERID")
                val passwd = data?.getStringExtra("LOGIN_PASSWD")
                Log.d("RESULT", "$userid / $passwd")
            }

        }
    }
}