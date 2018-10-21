package e.akhegai.rewall.data.remote

import retrofit2.Call

interface PostsApi {
    fun getHot(subreddit: String,
               limit: Int = 20,
               after: String = "",
               before: String = "",
               count: Int = 0): Call<RedditNewsResponse>
}