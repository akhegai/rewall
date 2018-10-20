package e.akhegai.rewall.ui.main

class MainPresenter: MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun attach(view: MainContract.View) {
        this.view = view
        view.showPostsFragment()
    }

    override fun onDrawerOptionAboutClick() {
        view.showPostsFragment()
    }
}