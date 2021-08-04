package com.example.peopledaliy.mvp.view.news

import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.peopledaliy.App
import com.example.peopledaliy.R
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.view.BaseAcyivity
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.di.DaggerLREComponent
import com.example.peopledaliy.mvp.di.LREModules
import com.example.peopledaliy.mvp.model.HttpCode
import com.example.peopledaliy.mvp.model.entity.CommentEntity
import com.example.peopledaliy.mvp.model.entity.UserEntity
import com.example.peopledaliy.mvp.presenter.LREPresenter
import com.example.peopledaliy.mvp.view.news.adapter.CommentAdapter
import com.example.peopledaliy.widget.x5.X5WebView
import com.google.android.material.appbar.AppBarLayout
import com.tb.emoji.Emoji
import com.tb.emoji.EmojiUtil
import com.tb.emoji.FaceFragment
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import io.reactivex.Observable
import okhttp3.RequestBody
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap
 class NewsValuesActivity : BaseAcyivity<LREPresenter>(), LREContract.ILREView, View.OnClickListener,
    FaceFragment.OnEmojiClickListener, AppBarLayout.OnOffsetChangedListener {
     var head_back_img: ImageView? = null
     var web_values_appbar: AppBarLayout? = null
     var live_msg_num_tv: TextView? = null
     var live_msg_relative: RelativeLayout? = null
     var web_values_x5: X5WebView? = null
     var web_values_comment_rv: RecyclerView? = null
     var live_edt_btn: RelativeLayout? = null
     var live_face_img: ImageView? = null
     var live_emoji_img: ImageView? = null
     var live_bottom_view: RelativeLayout? = null
     var live_emoji_linear: LinearLayout? = null
     var live_msg_edt: EditText? = null
     var live_send_tv: TextView? = null
     var headView: View? = null
     var commentAdapter: CommentAdapter? = null
     var news_id: Long = 0
     var news_url: String? = null
     var appFlag = true
    override fun onClick(v: View) {
        val id = v.id
        if (R.id.live_edt_btn === id) {
            live_bottom_view!!.visibility = View.VISIBLE
            live_emoji_linear!!.visibility = View.GONE
        } else if (R.id.live_face_img === id) {
            live_bottom_view!!.visibility = View.VISIBLE
            live_emoji_linear!!.visibility = View.VISIBLE
        } else if (R.id.live_emoji_img === id) {
            if (live_emoji_linear!!.visibility == View.VISIBLE) {
                live_emoji_linear!!.visibility = View.GONE
                live_emoji_img!!.setImageResource(R.drawable.res_7icon_129)
            } else {
                live_emoji_linear!!.visibility = View.VISIBLE
                live_emoji_img!!.setImageResource(R.drawable.res_7icon_131)
            }
        } else if (R.id.live_send_tv === id) {
            if (live_msg_edt!!.text == null) {
                return
            }
            if (live_msg_edt!!.text.toString().isEmpty()) {
                return
            }
            val entity: UserEntity =
                App.getInstance()!!.getDaoSession()!!.queryBuilder(UserEntity::class.java)
                    .list().get(0)
            val map: MutableMap<String, Any> = HashMap()
            map["code"] = HttpCode.ADDCOMMENTCODE
            map["comment_address"] = "天津"
            map["comment_time"] = System.currentTimeMillis() / 1000
            map["comment_value"] = live_msg_edt!!.text.toString()
            map["news_id"] = news_id
            map["user_id"] = entity.user_id
            //发布评论
            p!!.requestAll(map as HashMap<String, Any>)
        } else if (R.id.head_back_img === id) {
            finish()
        } else if (R.id.live_msg_relative === id) {
            if (appFlag) {
                live_msg_num_tv!!.text = "正文"
            } else {
                live_msg_num_tv!!.text = "" + 937
            }
            appFlag = !appFlag
            web_values_appbar!!.setExpanded(appFlag)
        }
    }

    override fun addStatusView(): View {
        return f(R.id.web_values_root)
    }

    override fun bindLayout(): Int {
        return R.layout.activity_web_values
    }

    override fun initView() {
        headView = LayoutInflater.from(this).inflate(R.layout.view_web_values, null)
        news_url = intent.extras!!.getString("news_url")
        news_id = intent.extras!!.getLong("news_id", 0)
        web_values_appbar = f(R.id.web_values_appbar)
        live_msg_relative = f(R.id.live_msg_relative)
        live_msg_num_tv = f(R.id.live_msg_num_tv)
        head_back_img = f(R.id.head_back_img)
        web_values_x5 = f(R.id.web_values_x5)
        live_edt_btn = f(R.id.live_edt_btn)
        live_face_img = f(R.id.live_face_img)
        live_bottom_view = f(R.id.live_bottom_view)
        live_emoji_linear = f(R.id.live_emoji_linear)
        live_emoji_img = f(R.id.live_emoji_img)
        live_send_tv = f(R.id.live_send_tv)
        live_msg_edt = f(R.id.live_msg_edt)
        web_values_comment_rv = f(R.id.web_values_comment_rv)
        web_values_x5!!.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(webView: WebView?, s: String): Boolean {
                return if (!s.contentEquals("http://118.195.161.134:8066/")) true else false
            }
        })
        getSupportFragmentManager().beginTransaction().add(
            R.id.live_emoji_linear,
            FaceFragment.Instance()
        ).commit()
        web_values_x5!!.loadUrl(news_url)
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.VERTICAL
        web_values_comment_rv!!.layoutManager = manager
        commentAdapter = CommentAdapter()
        commentAdapter!!.setEmptyView(R.layout.view_empty)
        commentAdapter!!.addHeaderView(headView!!)
        web_values_comment_rv!!.adapter = commentAdapter
        live_edt_btn!!.setOnClickListener { v: View -> onClick(v) }
        live_face_img!!.setOnClickListener { v: View -> onClick(v) }
        live_emoji_img!!.setOnClickListener { v: View -> onClick(v) }
        live_send_tv!!.setOnClickListener { v: View -> onClick(v) }
        head_back_img!!.setOnClickListener { v: View -> onClick(v) }
        live_msg_relative!!.setOnClickListener { v: View -> onClick(v) }
        web_values_appbar!!.addOnOffsetChangedListener(this)
    }

    override fun initData() {
        val map: MutableMap<String, Any> = HashMap()
        map["code"] = HttpCode.SELCOMMENTCODE
        map["news_id"] = news_id
        p!!.requestAll(map as HashMap<String, Any>)
    }
      fun initDat() {
         val map: MutableMap<String, Any> = HashMap()
         map["code"] = HttpCode.SELCOMMENTCODE
         map["news_id"] = news_id
         p!!.requestLoad(map as HashMap<String, Any>)
     }
     fun initDa() {
         val map: MutableMap<String, Any> = HashMap()
         map["code"] = HttpCode.SELCOMMENTCODE
         map["news_id"] = news_id
         p!!.requestLoa(map as HashMap<String, Any>)
     }

    override fun inject() {
        DaggerLREComponent.builder().lREModules(LREModules(this))
            .build().injectNewsValuesActivity(this)
    }
     var commentEntity: CommentEntity?=null
    override fun refreshAll(entity: BaseEntity?) {
        if (entity is CommentEntity) {
            commentEntity = entity as CommentEntity
            commentAdapter!!.setNewInstance(commentEntity!!.getValues() as MutableList<CommentEntity.Values>?)
        } else {
            if (entity!!.message.equals("发表成功")) {
                live_msg_edt!!.setText("")
                //刷新评论列表数据
                //请求获取最新评论列表
                if (commentEntity!!.getValues()!!.size==10){
                    initDat();
                }
                initDa()
            }
        }
    }

    override fun refreshRecyckerView(entity: BaseEntity?) {

    }
    override fun loadMoreRecyclerView(entity: BaseEntity?) {

    }

    override fun onEmojiDelete() {
        val text = live_msg_edt!!.text.toString()
        if (text.isEmpty()) {
            return
        }
        if ("]" == text.substring(text.length - 1, text.length)) {
            val index = text.lastIndexOf("[")
            if (index == -1) {
                val action = KeyEvent.ACTION_DOWN
                val code = KeyEvent.KEYCODE_DEL
                val event = KeyEvent(action, code)
                live_msg_edt!!.onKeyDown(KeyEvent.KEYCODE_DEL, event)
                try {
                    EmojiUtil.handlerEmojiText(live_msg_edt, live_msg_edt!!.text.toString(), this)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                return
            }
            live_msg_edt!!.text.delete(index, text.length)
            try {
                EmojiUtil.handlerEmojiText(live_msg_edt, live_msg_edt!!.text.toString(), this)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return
        }
        val action = KeyEvent.ACTION_DOWN
        val code = KeyEvent.KEYCODE_DEL
        val event = KeyEvent(action, code)
        live_msg_edt!!.onKeyDown(KeyEvent.KEYCODE_DEL, event)
        try {
            EmojiUtil.handlerEmojiText(live_msg_edt, live_msg_edt!!.text.toString(), this)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (live_bottom_view!!.visibility == View.VISIBLE) {
                live_bottom_view!!.visibility = View.GONE
            } else {
                finish()
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onEmojiClick(emoji: Emoji?) {
        if (emoji != null) {
            val index = live_msg_edt!!.selectionStart
            val editable = live_msg_edt!!.editableText
            if (index < 0) {
                editable.append(emoji.getContent())
            } else {
                editable.insert(index, emoji.getContent())
            }
            try {
                EmojiUtil.handlerEmojiText(live_msg_edt, live_msg_edt!!.text.toString(), this)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private var oldverticalOffset = 0
    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
//        LogUtils.show("onOffsetChanged:"+verticalOffset);
        if (oldverticalOffset == verticalOffset) return
        if (verticalOffset == 0) {
            //当前显示正文
            appFlag = true
            live_msg_num_tv!!.text = "" + 937
        } else {
            appFlag = false
            live_msg_num_tv!!.text = "正文"
        }
        oldverticalOffset = verticalOffset
    }
}
