package e.akhegai.rewall.di.component

import dagger.Component
import e.akhegai.rewall.RewallApplication
import e.akhegai.rewall.di.module.ApplicationModule

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: RewallApplication)

}