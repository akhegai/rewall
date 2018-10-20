package e.akhegai.rewall.di.component

import dagger.Component
import e.akhegai.rewall.di.module.ApplicationModule
import e.akhegai.rewall.di.module.NetworkModule
import e.akhegai.rewall.di.module.PostsModule
import e.akhegai.rewall.ui.posts.PostsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, PostsModule::class, NetworkModule::class])
interface PostsComponent {
    fun inject(postsFragment: PostsFragment)
}