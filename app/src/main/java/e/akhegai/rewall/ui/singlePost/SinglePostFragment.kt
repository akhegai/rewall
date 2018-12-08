package e.akhegai.rewall.ui.singlePost

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import e.akhegai.rewall.R
import e.akhegai.rewall.di.component.DaggerSinglePostComponent
import e.akhegai.rewall.di.module.SinglePostModule
import javax.inject.Inject


class SinglePostFragment : Fragment(), SinglePostContract.View {
    @Inject
    lateinit var presenter: SinglePostContract.Presenter
    private lateinit var rootView: View
    private lateinit var wallpaperManager: WallpaperManager
    private lateinit var imageBitmap: Bitmap


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.single_post, container, false)
        val button = rootView.findViewById<Button>(R.id.set_background_image_button)
        loadImage()
        setBackgroundClickHandler()
        wallpaperManager = WallpaperManager.getInstance(rootView.context)
        button.isEnabled = false
        button.setText(R.string.loading)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
    }

    private fun loadImage() {
        val image = rootView.findViewById<ImageView>(R.id.single_post_image)
        val button = rootView.findViewById<Button>(R.id.set_background_image_button)
        Glide.with(this)
                .asBitmap()
                .load(arguments?.getString("url"))
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        imageBitmap = resource
                        image.setImageBitmap(resource)
                        button.setText(R.string.set_background_image_button)
                        button.isEnabled = true
                    }
                })

    }

    private fun setBackgroundClickHandler() {
        val button = rootView.findViewById<Button>(R.id.set_background_image_button)
        button.setOnClickListener {
            wallpaperManager.setBitmap(imageBitmap)
            Snackbar.make(rootView, "This image was successfully set as a background", Snackbar.LENGTH_SHORT)
        }
    }

    private fun injectDependency() {
        val singlePostComponent = DaggerSinglePostComponent.builder()
                .singlePostModule(SinglePostModule())
                .build()

        singlePostComponent.inject(this)
    }

}