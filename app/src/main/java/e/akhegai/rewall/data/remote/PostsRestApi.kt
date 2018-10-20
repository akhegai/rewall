package e.akhegai.rewall.data.remote

import retrofit2.Call
import javax.inject.Inject

class PostsRestApi @Inject constructor(private val redditApi: RedditApi): PostsApi {
    override fun getPosts(subreddit: String, time: String, limit: String, after: String, before: String, count: Int): Call<RedditNewsResponse> {
        return this.redditApi.getTop(subreddit, time, limit, count.toString(), false, after, before)
    }

}