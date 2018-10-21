package e.akhegai.rewall.ui.posts

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
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
import kotlinx.android.synthetic.main.post_items_grid.*
import javax.inject.Inject

class PostsFragment : Fragment(), PostsContract.View, PostsAdapter.onPostClickListener {
    @Inject
    lateinit var presenter: PostsContract.Presenter
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

    override fun onLoadDataStart() {
        progress_bar.visibility = View.VISIBLE
        post_items_grid.visibility = View.GONE
        post_items_error_layout.visibility = View.GONE
    }

    override fun loadDataSuccess(posts: RedditPosts) {
        progress_bar.visibility = View.GONE
        post_items_grid.visibility = View.VISIBLE
        val context = requireActivity()
        val layoutManager = GridLayoutManager(context, 3)
        post_items_grid.layoutManager = layoutManager
        val adapter = PostsAdapter(context, posts.Posts.toMutableList(), this)
        post_items_grid.adapter = adapter
    }

    override fun loadDataFailure(message: String?) {
        progress_bar.visibility = View.GONE
        post_items_grid.visibility = View.GONE
        post_items_error_layout.visibility = View.VISIBLE
        post_items_error_text.text = message ?: getString(R.string.unknown_error_message)
        post_items_retry.setOnClickListener {
            onRetryButtonClick(it)
        }
    }

    override fun onItemClick(post: RedditPostsItem) {
        Log.e("onItemClick", post.toString())
    }

    override fun onRetryButtonClick(view: View) {
        presenter.loadData()
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