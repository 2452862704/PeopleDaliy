package com.example.peopledaliy.mvp.base.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.peopledaliy.mvp.base.presenter.IPresenter
import com.example.peopledaliy.widget.LoadDialog
import javax.inject.Inject


abstract class BaseFragment<P : IPresenter>: Fragment(),  IView,IFragment{
    @set:Inject
    protected var p: P? = null
    protected var rootView //当前fragment根结点视图
            : View? = null
    protected var loadDialog: LoadDialog? = null

    @Nullable
    override fun onCreateView(
        @NonNull inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(bindLayout(), container, false)
        loadDialog = LoadDialog(context!!)
        initView()
        inject()
        lifecycle.addObserver(p!!)
        initData()
        return rootView
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(p!!)
    }

    fun <T : View?> f(id: Int): T {
        return rootView!!.findViewById<T>(id)
    }

    override fun startPage(bundle: Bundle?, clazz: Class<*>?) {
        val intent = Intent(activity, clazz)
        if (bundle != null) intent.putExtras(bundle)
        activity!!.startActivity(intent)
    }

    fun startPage(clazz: Class<*>?) {
        startPage(null, clazz)
    }

    override fun showDialog() {
        loadDialog!!.show()
    }

    override fun hideDialog() {
        loadDialog!!.dismiss()
    }

     override fun showMsg(message: String) {
         Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
     }

 }