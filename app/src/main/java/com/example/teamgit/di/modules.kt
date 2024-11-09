package com.example.teamgit.di

import com.example.teamgit.data.net.createApiService
import org.koin.dsl.module
import kotlin.math.sin

val myModules = module {

    single { createApiService() }

}