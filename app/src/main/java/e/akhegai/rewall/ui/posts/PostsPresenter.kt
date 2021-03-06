package e.akhegai.rewall.ui.posts

import e.akhegai.rewall.common.RedditPosts
import e.akhegai.rewall.common.RedditPostsItem
import e.akhegai.rewall.data.remote.PostsApi
import e.akhegai.rewall.data.remote.RedditNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PostsPresenter @Inject constructor(private val postsApi: PostsApi) : PostsContract.Presenter {
    private lateinit var view: PostsContract.View

    override fun attach(view: PostsContract.View) {
        this.view = view
    }

    override fun loadData() {
        view.onLoadDataStart()
        postsApi.getHot("wallpaper", 100).enqueue(object : Callback<RedditNewsResponse> {
            override fun onFailure(call: Call<RedditNewsResponse>?, t: Throwable?) {
                view.loadDataFailure(t?.message)
            }

            override fun onResponse(call: Call<RedditNewsResponse>?, response: Response<RedditNewsResponse>?) {
                response?.let {
                    view.loadDataSuccess(process(response.body()!!))
                } ?: run {
                    view.loadDataFailure(null)
                }
            }

        })
    }

    private fun process(response: RedditNewsResponse): RedditPosts {
        val dataResponse = response.data
        val news = dataResponse.children
                .filter {
                    it.data.url.endsWith(".png") || it.data.url.endsWith(".jpg")
                }
                .map {
                    val item = it.data
                    RedditPostsItem(item.author, item.title, item.num_comments,
                            item.created, item.thumbnail, item.url)
                }
        return RedditPosts(
                dataResponse.after.orEmpty(),
                dataResponse.before.orEmpty(),
                news)
    }

}