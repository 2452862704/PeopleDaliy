package com.example.peopledaliy.mvp.base.view

import android.R
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils
import com.example.peopledaliy.mvp.base.presenter.IPresenter
import com.example.peopledaliy.widget.LoadDialog
import javax.inject.Inject


abstract class BaseAcyivity<P:IPresenter>:AppCompatActivity() ,IView,IActivity{
    @set:Inject
    protected var p: P? = null
    protected var loadDialog: LoadDialog? = null

    @SuppressLint("ResourceAsColor")
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindLayout())
        loadDialog = LoadDialog(this)
        //修改状态栏当中的图片以及文字颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            //把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
        }
        BarUtils.setStatusBarColor(this, Color.WHITE)
        //添加让出状态栏高度->防止contentView布局绘制到状态栏上
        BarUtils.addMarginTopEqualStatusBarHeight(addStatusView()!!)
        initView()
        inject()
        lifecycle.addObserver(p!!)
        initData()
    }

    open fun setStatuesColor(color: Int) {
        if (color == resources.getColor(R.color.white)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                //把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                or View.SYSTEM_UI_FLAG_VISIBLE)
                //把状态栏标记为浅色，然后状态栏的字体颜色自动转换为深色
            }
        }
        BarUtils.setStatusBarColor(this, color)
        BarUtils.addMarginTopEqualStatusBarHeight(addStatusView()!!)
    }

    abstract fun addStatusView(): View?

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(p!!)
    }

    override fun showDialog() {
        loadDialog!!.show()
    }

    override fun hideDialog() {
        loadDialog!!.dismiss()
    }

     override fun showMsg(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
     }

     open fun startPage(clazz: Class<*>?) {
         val bundle = Bundle()
         startPage(bundle, clazz)
    }

    protected open fun <T : View?> f(id: Int): T {
        return findViewById<T>(id)
    }

     override fun startPage(bundle: Bundle?, clazz: Class<*>?) {

         val intent = Intent(this, clazz)
         if (bundle != null) intent.putExtras(bundle)
         startActivity(intent)
     }
 }