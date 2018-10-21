package e.akhegai.rewall.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import e.akhegai.rewall.R
import e.akhegai.rewall.di.component.DaggerActivityComponent
import e.akhegai.rewall.di.module.ActivityModule
import e.akhegai.rewall.ui.posts.PostsFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

    @Inject lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        injectDependency()

        presenter.attach(this)
    }

    override fun showPostsFragment() {
        supportFragmentManager.beginTransaction()
                .disallowAddToBackStack()
                .replace(R.id.frame, PostsFragment(), PostsFragment.TAG)
                .commit()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }
}
