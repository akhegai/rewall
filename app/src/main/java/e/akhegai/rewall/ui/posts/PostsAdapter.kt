package e.akhegai.rewall.ui.posts

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import e.akhegai.rewall.R
import e.akhegai.rewall.common.RedditPostsItem

class PostsAdapter(private val mContext: Context, private val posts: MutableList<RedditPostsItem>,
                   fragment: Fragment) : RecyclerView.Adapter<PostsAdapter.PostViewHolder>() {

    private val listener: PostsAdapter.onPostClickListener

    init {
        this.listener = fragment as PostsAdapter.onPostClickListener
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, vieπwType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(mContext).inflate(R.layout.post_item_layout, parent, false)
        return PostViewHolder(itemView)
    }

    fun addPosts(postsToAdd: MutableList<RedditPostsItem>) {
        val initPosition = posts.size - 1
        posts.addAll(postsToAdd)
        notifyItemRangeChanged(initPosition, postsToAdd.size)
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image = itemView.findViewById<ImageView>(R.id.grid_post_item_image)

        fun bind(item: RedditPostsItem) {
            image.setOnClickListener {
                listener.onItemClick(item)
            }
            Glide.with(mContext)
                    .load(item.thumbnail)
                    .into(image)
        }
    }

    interface onPostClickListener {
        fun onItemClick(post: RedditPostsItem)
        fun onRetryButtonClick(view: View)
    }
}