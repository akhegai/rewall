package e.akhegai.rewall.ui.singlePost

import android.util.Log


class SinglePostPresenter : SinglePostContract.Presenter {
    private lateinit var view: SinglePostContract.View

    override fun attach(view: SinglePostContract.View) {
        this.view = view
    }

    override fun setBackground() {
        Log.i("SinglePostPresenter", "set background called")
    }

}