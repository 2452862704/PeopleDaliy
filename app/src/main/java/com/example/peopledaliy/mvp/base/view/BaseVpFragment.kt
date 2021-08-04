package com.example.peopledaliy.mvp.base.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.peopledaliy.mvp.base.presenter.IPresenter
import com.example.peopledaliy.widget.LoadDialog
import javax.inject.Inject

abstract open class BaseVpFragment<P : IPresenter> : Fragment(),
    IView, IFragment {
    @set:Inject
    protected var p: P? = null
    protected var rootView //当前fragment根结点视图
            : View? = null
    protected var loadDialog: LoadDialog? = null
    private var isFirst = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(bindLayout(), container, false)
        loadDialog = context?.let { LoadDialog(it) }
        initView()
        inject()
        p?.let { lifecycle.addObserver(it) }
        //        if (isFirst&&getUserVisibleHint())
        init()
        return rootView
    }

    private fun init() {
        isFirst = false
        initData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onDestroy() {
        super.onDestroy()
        p?.let { lifecycle.removeObserver(it) }
    }

    fun <T : View?> f(id: Int): T {
        return rootView!!.findViewById(id)
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

     }

 }
