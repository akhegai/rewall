package e.akhegai.rewall.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import e.akhegai.rewall.RewallApplication
import e.akhegai.rewall.di.scope.PerApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: RewallApplication) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return app
    }
}