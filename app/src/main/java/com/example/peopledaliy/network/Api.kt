package com.example.peopledaliy.network

import com.example.peopledaliy.mvp.base.model.BaseEntity
import com.example.peopledaliy.mvp.model.entity.*
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface Api {
    //    String BaseUrl = "http://118.195.161.134:8088/";//生产环境地址
    companion object{
        var BaseUrl = "http://118.195.161.134:8066/"
        var FileUrl = "http://118.195.161.134:8066/fileDownload?fileName="
    }

    @POST("sysToken/getToken")
    fun requestToken(@Body body: RequestBody?): Observable<ToKenEntity>

    @POST("sysUser/loginUser")
    fun requestLogin(@Body body: RequestBody?): Observable<ResponseUserEntity>

    @POST("sysUser/registerUser")
    fun requestRegister(@Body body: RequestBody?): Observable<ResponseUserEntity>

    @POST("sysUser/userValues")
    fun requestUser(@Body body: RequestBody?): Observable<ResponseUserEntity>

    @POST("sysUser/updateUser")
    fun requestUpdateUser(@Body body: RequestBody?): Observable<UpdateUserEntity>

    @POST("sysNews/channelNews")
    fun requestNews(@Body body: RequestBody?): Observable<NewsEntity>

    @POST("sysChannel/moreChannel")
    fun requestUserChannels(@Body body: RequestBody?): Observable<ChannelEntity>

    @POST("sysBanner/textBannerNews")
    fun requestTextBanner(): Observable<TextBannerEntity>

    @POST("sysBanner/bannerNews")
    fun requestImageBanner(): Observable<ImageBannerEntity>

    @POST("sysComment/selComment")
    fun requestComments(@Body body: RequestBody?): Observable<CommentEntity>

    @POST("sysComment/addComment")
    fun requestaddComment(@Body body: RequestBody?): Observable<BaseEntity>

    @POST("sysLiveComment/addComment")
    fun requestaddLiveComment(@Body body: RequestBody?): Observable<BaseEntity>

    @POST("sysChannel/collectionChannel")
    fun requestCollectionChannels(@Body body: RequestBody?): Observable<BaseEntity>

    @Multipart
    @POST("fileUpload")
    fun requestUploadHead(@Part parts: List<MultipartBody.Part?>?): Observable<UploadFileEntity>

    @POST("sysNews/videoNews")
    fun requestVideoNews(@Body body: RequestBody?): Observable<VideoNewsEntity>

    @POST("sysNews/hotNews")
    fun requestRecommendNews(): Observable<RecommendEntity>

}