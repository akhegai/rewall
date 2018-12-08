package e.akhegai.rewall.di.component

import dagger.Component
import e.akhegai.rewall.di.module.ApplicationModule
import e.akhegai.rewall.di.module.SinglePostModule
import e.akhegai.rewall.ui.singlePost.SinglePostFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, SinglePostModule::class])
interface SinglePostComponent {
    fun inject(singlePostFragment: SinglePostFragment)
}