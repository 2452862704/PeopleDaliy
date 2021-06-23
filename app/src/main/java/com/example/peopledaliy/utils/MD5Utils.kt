package com.example.peopledaliy.utils

import com.blankj.utilcode.util.EncryptUtils
import java.util.*


class MD5Utils {
    fun createSign(oldStr:String):String{
        if (oldStr.isEmpty()) return ""
        val arrs = oldStr.toCharArray()
        Arrays.sort(arrs)
        val str = String(arrs)
        return EncryptUtils.encryptMD5ToString(str + "tamboo").toLowerCase()
    }
}