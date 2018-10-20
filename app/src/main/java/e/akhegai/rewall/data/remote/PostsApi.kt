package e.akhegai.rewall.data.remote

import retrofit2.Call

interface PostsApi {
    fun getPosts(subreddit: String,
                 time: String,
                 limit: String = "10",
                 after: String = "",
                 before: String = "",
                 count: Int = 0): Call<RedditNewsResponse>
}