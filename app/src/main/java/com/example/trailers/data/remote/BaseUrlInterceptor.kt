package com.example.trailers.data.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class BaseUrlInterceptor : Interceptor {
    private val REQUEST_HEADER_AUTH_TOKEN =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1ZDZlNWU5ODZhNWZlNjcxOWNmYTc4MTVjNTFlODAxNyIsInN1YiI6IjY1MjUzM2QxZDM5OWU2MDEzYTNjYzcwMyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.U9VCywO-rlWSOGfwQy5nGI4S8XeBplhq3hb2JEtsuR0"

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request

        request = chain.request().newBuilder()
            .addHeader("Authorization", REQUEST_HEADER_AUTH_TOKEN)
            .build()
        return chain.proceed(request)
    }
}