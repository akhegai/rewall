package e.akhegai.rewall.ui.posts

import e.akhegai.rewall.common.RedditPosts
import e.akhegai.rewall.common.RedditPostsItem
import e.akhegai.rewall.ui.base.BaseContract

class PostsContract {

    interface View: BaseContract.View {
        fun onLoadDataStart()
        fun loadDataSuccess(posts: RedditPosts)
        fun loadDataFailure(message: String?)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
    }
}