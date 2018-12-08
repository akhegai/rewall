package e.akhegai.rewall.di.module

import dagger.Module
import dagger.Provides
import e.akhegai.rewall.ui.singlePost.SinglePostContract
import e.akhegai.rewall.ui.singlePost.SinglePostPresenter
import javax.inject.Singleton

@Module
class SinglePostModule {
    @Provides
    @Singleton
    fun provideSinglePostPresenter(): SinglePostContract.Presenter = SinglePostPresenter()
}