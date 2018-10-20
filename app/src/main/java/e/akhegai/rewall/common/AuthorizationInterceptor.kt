package e.akhegai.rewall.common

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton


@Singleton
class AuthorizationInterceptor: Interceptor {

    lateinit var token: String

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()

        original.header("No-Authentication")?.let {
            original = original.newBuilder()
                    .addHeader("Authroization", "123")
                    .build()
        }
        return chain.proceed(original)
    }

}