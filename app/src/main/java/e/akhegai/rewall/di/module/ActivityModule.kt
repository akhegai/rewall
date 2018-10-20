package e.akhegai.rewall.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import e.akhegai.rewall.ui.main.MainContract
import e.akhegai.rewall.ui.main.MainPresenter
import javax.inject.Singleton

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    @Singleton
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @Singleton
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}