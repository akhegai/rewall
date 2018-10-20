package e.akhegai.rewall.di.component

import dagger.Component
import e.akhegai.rewall.di.module.ActivityModule
import e.akhegai.rewall.di.module.NetworkModule
import e.akhegai.rewall.ui.main.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [ActivityModule::class, NetworkModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}