package com.example.peopledaliy.network.interceptor

import com.blankj.utilcode.util.LogUtils
import com.example.peopledaliy.utils.MD5Utils
import com.example.peopledaliy.utils.SpUtils
import okhttp3.*
import okio.Buffer
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class SignInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val build = Request.Builder()
        build.url(oldRequest.url())
        build.headers(oldRequest.headers())
        val oldBody = oldRequest.body()
        //获取requestbody中字符串
        val buffer = Buffer()
        oldBody!!.writeTo(buffer)
        var oldJson = ""
        val buff = ByteArray(1024)
        while (buffer.read(buff) != -1) {
            oldJson += String(buff)
        }
        buffer.close()
        oldJson.trim { it <= ' ' }
        //        LogUtils.e("oldJson:"+oldJson);
        //[text={json}]
//        oldJson = oldJson.substring(6,oldJson.lastIndexOf("}"));
//        oldJson+="}";
//        LogUtils.e("oldJson:"+oldJson);
        var values = ""
        var json = ""
        //由于每个接口当中请求参数不同，所以oldjson当中的key不能确定
        try {
            val job = JSONObject(oldJson)
            //获取全部json中包含的key
            val it = job.keys()
            while (it.hasNext()) {
                values += job.getString(it.next())
            }
            val sign: String = MD5Utils().createSign(values)
            job.put("sign", sign)
            json = job.toString()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        LogUtils.i("json:"+ json)
        val body = RequestBody.create(MediaType.parse("application/json"), json)
        build.post(body)
        return chain.proceed(build.build())
    }
}
