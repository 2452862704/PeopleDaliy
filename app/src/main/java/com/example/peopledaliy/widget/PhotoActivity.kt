package com.example.peopledaliy.widget

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File

class PhotoActivity : AppCompatActivity() {
    private val REQUESTPERMISSIONCODE = 100
    private val REQUESTPHOTOCODE = 101
    private val REQUESTCAMERACODE = 102
    private var uri //开启相机时，传递到相机设置相机保存图片路径
            : Uri? = null
    private var file //开启相机时，保存图片路径
            : File? = null
    private var type = -1 //popupwindow开启是传递过来参数，调用开启相机/图库
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        type = intent.getIntExtra("type", -1)
        requestPermission()
        if (type == CAMERACODE) {
            //开启图库
            openCamera()
        } else if (type == PHOTOCODE) {
            //开启相机
            openPhoto()
        }
    }

    //申请权限方法
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA
                ),
                REQUESTPERMISSIONCODE
            )
        }
    }

    //获取申请结果
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        var flag = true
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) flag = false
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) flag = false
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) flag = false
        if (!flag) {
            finish()
        }
        if (type == 0) {
            //开启图库
            openPhoto()
        } else if (type == 1) {
            //开启相机
            openCamera()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != RESULT_OK) {
            finish()
            return
        }
        //根据requestCode区分图库或相机
        if (REQUESTPHOTOCODE == requestCode) {
            val cursor = contentResolver.query(
                data!!.data!!, arrayOf(MediaStore.Images.Media.DATA),
                null, null, null
            )
            var path: String? = null
            while (cursor!!.moveToNext()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            }
            cursor.close()
            val intent = Intent()
            intent.putExtra("path", path)
            setResult(RESULT_OK, intent)
            finish()
        }
        if (REQUESTCAMERACODE == requestCode) {
            var path: String? = null
            path = file!!.absolutePath
            val intent = Intent()
            intent.putExtra("nick", path)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    //开启图库
    private fun openPhoto() {
        val intent = Intent()
        intent.action = Intent.ACTION_PICK
        intent.type = "image/*"
        startActivityForResult(intent, REQUESTPHOTOCODE)
    }

    //开启相机
    private fun openCamera() {
        val name = "" + System.currentTimeMillis()
        file = File(
            getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.absolutePath,
            "$name.jpg"
        )
        //判断SDK版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            //SDK版本大于10->兼容处理沙箱机制
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME, name)
            values.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures")
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/JPEG")
            uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        }
        uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //7-9
            FileProvider.getUriForFile(this, "com.example.peopledaliy.camera_file", file!!)
        } else {
            //低版本
            Uri.fromFile(file)
        }
        val intent = Intent()
        intent.action = MediaStore.ACTION_IMAGE_CAPTURE
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
        startActivityForResult(intent, REQUESTCAMERACODE)
    }

    companion object {
        var CAMERACODE = 0
        var PHOTOCODE = 1
    }
}
