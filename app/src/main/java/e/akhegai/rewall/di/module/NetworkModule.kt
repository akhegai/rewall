package e.akhegai.rewall.di.module

import dagger.Module
import dagger.Provides
import e.akhegai.rewall.common.AuthorizationInterceptor
import e.akhegai.rewall.common.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor())
                .build()
        return Retrofit.Builder()
                .baseUrl(Constants.REDDIT_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}