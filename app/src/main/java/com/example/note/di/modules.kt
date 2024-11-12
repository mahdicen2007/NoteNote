package com.example.note.di

import com.example.note.data.net.createApiService
import org.koin.dsl.module

val myModules = module {

    single { createApiService() }

}