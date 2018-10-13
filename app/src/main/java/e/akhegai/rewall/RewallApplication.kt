package e.akhegai.rewall

import android.app.Application
import e.akhegai.rewall.di.component.ApplicationComponent
import e.akhegai.rewall.di.component.DaggerApplicationComponent
import e.akhegai.rewall.di.module.ApplicationModule

class RewallApplication: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: RewallApplication private set
    }
}