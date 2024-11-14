package com.example.note.di

import android.content.Context
import androidx.room.Room
import com.example.note.data.net.createApiService
import com.example.note.data.repo.HomeRepoImpl
import com.example.note.data.repo.HomeRepository

import com.example.note.data.room.MyDatabase
import com.example.note.ui.features.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModules = module {

    single { androidContext().getSharedPreferences("data", Context.MODE_PRIVATE) }
    single { createApiService() }
    single {
        Room.databaseBuilder(
            androidContext(), MyDatabase::class.java, "AppDatabase.db"
        )
            .build()
    }

    // Repositories
    single<HomeRepository> {
        HomeRepoImpl(
            get<MyDatabase>().noteDao()
        )
    }

    // ViewModels
    viewModel { HomeViewModel(get()) }

}