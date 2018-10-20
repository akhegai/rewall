package e.akhegai.rewall.di.module

import dagger.Module
import dagger.Provides
import e.akhegai.rewall.data.remote.PostsApi
import e.akhegai.rewall.data.remote.PostsRestApi
import e.akhegai.rewall.data.remote.RedditApi
import e.akhegai.rewall.ui.posts.PostsContract
import e.akhegai.rewall.ui.posts.PostsPresenter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class PostsModule {
    @Provides
    @Singleton
    fun providePostsApi(redditApi: RedditApi): PostsApi = PostsRestApi(redditApi)

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi = retrofit.create(RedditApi::class.java)

    @Provides
    @Singleton
    fun providePostsPresenter(postsApi: PostsApi): PostsContract.Presenter = PostsPresenter(postsApi)
}