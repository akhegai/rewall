package e.akhegai.rewall.common

import android.os.Parcel
import android.os.Parcelable

data class RedditPosts(
        val after: String,
        val before: String,
        val Posts: List<RedditPostsItem>) : Parcelable {
    companion object {
        @Suppress("unused")
        @JvmField val CREATOR: Parcelable.Creator<RedditPosts> = object : Parcelable.Creator<RedditPosts> {
            override fun createFromParcel(source: Parcel): RedditPosts = RedditPosts(source)
            override fun newArray(size: Int): Array<RedditPosts?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.createTypedArrayList(RedditPostsItem.CREATOR)!!)

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(after)
        dest?.writeString(before)
        dest?.writeTypedList(Posts)
    }
}

data class RedditPostsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String?
) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<RedditPostsItem> = object : Parcelable.Creator<RedditPostsItem> {
            override fun createFromParcel(source: Parcel): RedditPostsItem = RedditPostsItem(source)
            override fun newArray(size: Int): Array<RedditPostsItem?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readInt(), source.readLong(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(author)
        dest?.writeString(title)
        dest?.writeInt(numComments)
        dest?.writeLong(created)
        dest?.writeString(thumbnail)
        dest?.writeString(url)
    }
}