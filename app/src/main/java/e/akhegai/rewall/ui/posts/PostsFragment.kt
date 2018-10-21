package e.akhegai.rewall.ui.posts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import e.akhegai.rewall.R
import e.akhegai.rewall.common.RedditPosts
import e.akhegai.rewall.common.RedditPostsItem
import e.akhegai.rewall.di.component.DaggerPostsComponent
import e.akhegai.rewall.di.module.PostsModule
import javax.inject.Inject

class PostsFragment : Fragment(), PostsContract.View, PostsAdapter.onPostClickListener {
    @Inject lateinit var presenter: PostsContract.Presenter
    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.post_items_grid, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun loadDataSuccess(posts: RedditPosts) {
        val adapter = PostsAdapter(requireActivity(), posts.Posts.toMutableList(), this)
        Log.i("loadDataSuccess", "posts")
        val gridview: GridView = view!!.findViewById(R.id.post_items_grid)
        gridview.adapter = adapter
    }


    override fun onClick(post: RedditPostsItem) {
        Log.e("onClick", post.toString())
    }

    private fun injectDependency() {
        val postsComponent = DaggerPostsComponent.builder()
                .postsModule(PostsModule())
                .build()

        postsComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }

    companion object {
        val TAG: String = "PostsFragment"
    }
}