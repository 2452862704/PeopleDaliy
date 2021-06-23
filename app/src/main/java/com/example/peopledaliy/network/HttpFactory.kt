package com.example.peopledaliy.network

import com.example.peopledaliy.network.HttpImpl

class HttpFactory {

    companion object{
        var instance: HttpFactory? = null
        get() {
            if (field == null){
                field=HttpFactory()
            }
            return  field
        }
    }
    private var upload: HttpImpl? = null
    private  var sign:HttpImpl? = null
    private  var tokenSign:HttpImpl? = null
    private  var token:HttpImpl? = null
    fun factory(type: HttpType?): HttpImpl? {
        var http: HttpImpl? = null
        when (type) {
            HttpType.UPLOADTYPE -> {
                if (upload == null) upload =UploadRetrofit.Build().build()
                http = upload
            }
            HttpType.SIGNTYPE -> {
                if (sign == null) sign = SignRetrofit.Build().build()
                http = sign
            }
            HttpType.TOKENSIGNTYPE -> {
                if (tokenSign == null) tokenSign = TokenSignRetrofit.Build().build()
                http = tokenSign
            }
            HttpType.TOKEN -> {
                if (token == null) token = TokenRetrofit.Build().build()
                http = token
            }
        }
        return http
    }
}