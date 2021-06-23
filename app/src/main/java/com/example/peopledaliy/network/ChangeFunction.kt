package com.example.peopledaliy.network

import com.example.peopledaliy.mvp.base.model.BaseEntity
import io.reactivex.functions.Function

class ChangeFunction<T : BaseEntity?> : Function<T, BaseEntity> {

    override fun apply(t: T): T {
        return t
    }
}