package com.example.swacademy_android.di

import com.example.swacademy_android.data.service.DetailService
import com.example.swacademy_android.data.service.HomeService
import com.example.swacademy_android.data.service.MypageService
import com.example.swacademy_android.data.service.RentalService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent :: class)
@Module
object ServiceModule {
    @Singleton
    @Provides
    fun provideHomeService(retrofit: Retrofit): HomeService =
        retrofit.create(HomeService::class.java)

    @Singleton
    @Provides
    fun provideRentalService(retrofit: Retrofit): RentalService =
        retrofit.create(RentalService::class.java)

    @Singleton
    @Provides
    fun provideDetailService(retrofit: Retrofit): DetailService =
        retrofit.create(DetailService::class.java)

    @Singleton
    @Provides
    fun providMypageService(retrofit: Retrofit): MypageService =
        retrofit.create(MypageService::class.java)
}