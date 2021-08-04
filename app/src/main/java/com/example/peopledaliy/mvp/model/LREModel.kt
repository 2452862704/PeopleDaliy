package com.example.peopledaliy.mvp.model

import android.util.Log
import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.base.model.BaseModel
import com.example.peopledaliy.mvp.contract.LREContract
import com.example.peopledaliy.mvp.model.entity.*
import com.example.peopledaliy.network.*
import io.reactivex.Observable


class LREModel :BaseModel() , LREContract.ILREModel {



    override fun requestAll(vararg maps: HashMap<String, Any>): Observable<BaseEntity> {
        //根据传递进来的maps 数量创建生成专门用来存放被观察者的集合
        Log.i("fkt", "requestAll: "+maps[0].keys)
        val observables: MutableList<Observable<BaseEntity>?> = ArrayList()
        for (i in 0 until maps.size) {
            val map: HashMap<String, Any> = maps[i]
            observables.add(change2BaseEntity(map!!))
        }
        return Observable.merge(observables)
    }

    override fun request_refresh_load(map: MutableMap<String, Any>): Observable<BaseEntity>? {
        return change2BaseEntity(map!! as HashMap<String, Any>)
    }


    private val upload: HttpImpl? = null
    private  var sign:HttpImpl? = null
    private  var tokenSign: HttpImpl? = null
    private  var token:HttpImpl? = null
    private fun change2BaseEntity(map: HashMap<String, Any>): Observable<BaseEntity>? {
        val code = map["code"] as Int
        Log.i("fkt", "change2BaseEntity: "+map)

        map.remove("code")
        var ob: Observable<BaseEntity>? = null
        when (code) {
            HttpCode.USERCHANNELCODE ->
                ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                    .getRetrofit()!!.create(Api::class.java)
                    .requestUserChannels(createBody(map))
                    .map(ChangeFunction<ChannelEntity>())
            HttpCode.CHANNELNEWS ->
                ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                    .getRetrofit()!!.create(Api::class.java)
                    .requestNews(createBody(map))
                    .map(ChangeFunction<NewsEntity>())
            HttpCode.IMAGERBANNERCODE -> ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                .getRetrofit()!!.create(Api::class.java)
                .requestImageBanner().map(ChangeFunction<ImageBannerEntity>())
            HttpCode.TEXTBANNERCODE -> ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                .getRetrofit()!!.create(Api::class.java)
                .requestTextBanner().map(ChangeFunction<TextBannerEntity>())
            HttpCode.SELCOMMENTCODE -> ob =
                HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                    .getRetrofit()!!.create(Api::class.java)
                    .requestComments(createBody(map))
                    .map(ChangeFunction<CommentEntity>())
            HttpCode.ADDCOMMENTCODE -> ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                .getRetrofit()!!.create(Api::class.java)
                .requestaddComment(createBody(map))
            HttpCode.ADDLIVECOMMENTCODE -> ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                .getRetrofit()!!.create(Api::class.java)
                .requestaddLiveComment(createBody(map))
            HttpCode.COLLECTIONCHANNELCODE -> ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                .getRetrofit()!!.create(Api::class.java)
                .requestCollectionChannels(createBody(map))
            HttpCode.VIDEONEWSCODE->
                ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                    .getRetrofit()!!.create(Api::class.java)
                    .requestVideoNews(createBody(map)).map(
                        ChangeFunction<VideoNewsEntity>())
            HttpCode.RECOMMENDNEWSCODE->
                ob = HttpFactory.instance!!.factory(HttpType.TOKENSIGNTYPE)!!
                    .getRetrofit()!!.create(Api::class.java)
                    .requestRecommendNews()
                    .map(ChangeFunction<RecommendEntity>())
        }
        return ob
    }

}