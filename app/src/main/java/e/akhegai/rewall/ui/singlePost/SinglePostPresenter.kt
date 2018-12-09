package e.akhegai.rewall.ui.singlePost


class SinglePostPresenter : SinglePostContract.Presenter {
    private lateinit var view: SinglePostContract.View

    override fun attach(view: SinglePostContract.View) {
        this.view = view
    }

}