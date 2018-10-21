package e.akhegai.rewall.data.remote

import retrofit2.Call
import javax.inject.Inject

class PostsRestApi @Inject constructor(private val redditApi: RedditApi): PostsApi {
    override fun getHot(subreddit: String, limit: Int, after: String, before: String, count: Int): Call<RedditNewsResponse> {
        return this.redditApi.getHot(subreddit, limit.toString(), count.toString(), false, after, before)
    }

}