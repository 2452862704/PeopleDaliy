package com.example.peopledaliy.mvp.view.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.peopledaliy.R

class UserActivity : AppCompatActivity() {
    val loginFragment = LoginFragment()
    val registerFragment = RegisterFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        supportFragmentManager.beginTransaction().add(R.id.ll,loginFragment).add(R.id.ll,registerFragment).commit()
        supportFragmentManager.beginTransaction().replace(R.id.ll,loginFragment).remove(registerFragment).commit()
    }
}