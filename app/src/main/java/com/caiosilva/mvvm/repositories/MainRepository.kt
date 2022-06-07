package com.caiosilva.mvvm.repositories

import com.caiosilva.mvvm.rest.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllLives() = retrofitService.getAllLives()

}