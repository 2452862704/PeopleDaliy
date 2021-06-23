package com.example.peopledaliy.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.peopledaliy.R

class TestActivity : AppCompatActivity() {
    private val mRv3 by lazy { findViewById<MyViewGroup>(R.id.rv3) }
    private val mRv2 by lazy { findViewById<MyViewGroup>(R.id.rv2) }
    private val mRv1 by lazy { findViewById<MyViewGroup>(R.id.rv1) }
    val arrayList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        for (i in 1..20){
            arrayList.add("数据："+i)
        }
        val myAdapter = MyAdapter(arrayList)
        mRv1.adapter=myAdapter
        mRv2.adapter=myAdapter
        mRv3.adapter=myAdapter
    }
    private inner class MyAdapter(val list: ArrayList<String>):BaseAdapter(){
        override fun getCount(): Int {
            return list.size
        }

        override fun getItem(position: Int): Any {
            return list.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflate = LayoutInflater.from(this@TestActivity).inflate(R.layout.item_test, null)
            inflate.findViewById<TextView>(R.id.test_tv).setText(list.get(position))
            return inflate
        }

    }
}