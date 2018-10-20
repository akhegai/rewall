package e.akhegai.rewall.ui.posts

import android.content.Context
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import e.akhegai.rewall.R
import e.akhegai.rewall.common.RedditPostsItem

class PostsAdapter(private val mContext: Context, private val posts: MutableList<RedditPostsItem>,
                   fragment: Fragment) : BaseAdapter() {

    private val listener: PostsAdapter.onPostClickListener

    init {
        this.listener = fragment as PostsAdapter.onPostClickListener
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView
                ?: LayoutInflater.from(mContext).inflate(R.layout.post_item_layout, parent, false)
        Glide.with(mContext)
                .load(posts[position])
                .into(view as ImageView)
        return view
    }

    override fun getItem(position: Int): Any = posts[position]

    override fun getItemId(position: Int): Long = 0L

    override fun getCount(): Int = posts.size

    interface onPostClickListener {
        fun onClick(post: RedditPostsItem)
    }
}