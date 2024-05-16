package com.welkinwits.di

import android.content.Context


import com.welkinwits.util.DataStoreManager
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    @Singleton
    fun dataStoreManager(@ApplicationContext appContext: Context): DataStoreManager =
        DataStoreManager(appContext)
}