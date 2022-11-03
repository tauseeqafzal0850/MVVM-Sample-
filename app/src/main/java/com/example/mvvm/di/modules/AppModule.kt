package com.example.mvvm.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.mvvm.data.implementations.SharedPrefRepositoryImpl
import com.example.mvvm.data.database.AppDatabase
import com.example.mvvm.di.domain.local.UserDao
import com.example.mvvm.di.domain.remote.ApiService
import com.example.mvvm.di.domain.repository.SharedPrefRepository
import com.example.mvvm.di.domain.repository.UserRepository
import com.example.mvvm.presentation.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideSharedPref(
        @ApplicationContext context: Context,
    ) = SharedPrefRepositoryImpl(context) as SharedPrefRepository

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        return Retrofit.Builder()
            .baseUrl(Constants.BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providesUserDao(userDatabase: AppDatabase):UserDao = userDatabase.userDao()

    @Provides
    @Singleton
    fun providesUserDatabase(@ApplicationContext context: Context):AppDatabase
            = Room.databaseBuilder(context,AppDatabase::class.java,"UserDatabase").build()

    @Provides
    fun providesUserRepository(userDao: UserDao) : UserRepository
            = UserRepository(userDao)

    @Provides
    @Singleton
    fun provideEndPointService(
        retrofit: Retrofit,
    ): ApiService = retrofit.create(ApiService::class.java)
}