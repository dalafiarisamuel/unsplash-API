package ng.devtamuno.unsplash.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ng.devtamuno.blurhash.BlurHash
import ng.devtamuno.unsplash.networking.ApiInterface
import ng.devtamuno.unsplash.networking.ConnectionDetector
import ng.devtamuno.unsplash.networking.RetrofitCompat
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @[Provides Singleton]
    fun providesRetrofit(): Retrofit = RetrofitCompat.getInstance()

    @[Provides Singleton]
    fun providesApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @[Provides Singleton]
    fun providesConnectionDetector(@ApplicationContext context: Context) =
        ConnectionDetector(context)

    @[Provides Singleton]
    fun provideBlurHash(@ApplicationContext context: Context) =
        BlurHash(context, lruSize = 20, punch = 1F)

}