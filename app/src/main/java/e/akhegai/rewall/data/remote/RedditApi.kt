package e.akhegai.rewall.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RedditApi {
    @GET("/r/{subreddit}/top.json")
    fun getTop(@Path(value = "subreddit", encoded = true) subreddit: String,
               @Query("time") time: String,
               @Query("limit") limit: String,
               @Query("count") count: String,
               @Query("include_categories") categories: Boolean,
               @Query("after") after: String,
               @Query("before") before: String
               ): Call<RedditNewsResponse>

}