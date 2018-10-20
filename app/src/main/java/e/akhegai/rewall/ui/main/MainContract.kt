package e.akhegai.rewall.ui.main

import e.akhegai.rewall.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
        fun showPostsFragment()
    }

    interface Presenter: BaseContract.Presenter<MainContract.View> {
        fun onDrawerOptionAboutClick()
    }
}