package com.example.peopledaliy.mvp.view.user

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.example.peopledaliy.R

class UserNameActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_name)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            //把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
        }
        BarUtils.setStatusBarColor(this, Color.WHITE)
        //添加让出状态栏高度->防止contentView布局绘制到状态栏上
        BarUtils.addMarginTopEqualStatusBarHeight(findViewById(R.id.user_name_root))
        findViewById<View>(R.id.user_nick_save_btn).setOnClickListener { v: View -> onClick(v)
        }
    }

    override fun onClick(v: View) {
        val editText = findViewById<EditText>(R.id.user_nick_edt)
        if (editText.text == null) {
            ToastUtils.showShort("请输入昵称")
            return
        }
        if (editText.text.toString().isEmpty()) {
            ToastUtils.showShort("请输入昵称")
            return
        }
        val intent = Intent()
        intent.putExtra("nick", editText.text.toString())
        setResult(RESULT_OK, intent)
        finish()
    }
}
