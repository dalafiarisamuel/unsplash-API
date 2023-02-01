package ng.devtamuno.unsplash.views.fragment

import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import ng.devtamuno.blurhash.BlurHash
import ng.devtamuno.blurhash.blurPlaceHolder
import ng.devtamuno.unsplash.R
import ng.devtamuno.unsplash.model.UnsplashPhoto
import ng.devtamuno.unsplash.views.custom.AspectRatioImageView
import javax.inject.Inject

@AndroidEntryPoint
class ImagePreviewFragment : DialogFragment() {

    companion object {

        const val TAG = "ImagePreviewFragment"
        private const val data: String = "PhotoData"

        @JvmStatic
        fun newInstance(photo: UnsplashPhoto?): ImagePreviewFragment {
            val fragment = ImagePreviewFragment()
            val args = Bundle()
            args.putParcelable(data, photo)
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var blurHash: BlurHash

    private var photo: UnsplashPhoto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        photo = arguments?.getParcelable(data)
    }

    override fun show(manager: FragmentManager, tag: String?) {
        try {
            val ft = manager.beginTransaction()
            ft.add(this, tag)
            ft.commitAllowingStateLoss()
        } catch (ignored: IllegalStateException) {
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.dialog_image_preview, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView: AspectRatioImageView = view.findViewById(R.id.aspectRatioImageView)
        val description: TextView = view.findViewById(R.id.description)
        val transparentView: View = view.findViewById(R.id.transparentView)

        photo?.apply {

            imageView.aspectRatio =
                photo!!.height.toDouble() / photo!!.width.toDouble()

            Glide.with(imageView)
                .load(photo?.urls?.regular)
                .transition(DrawableTransitionOptions.withCrossFade())
                .blurPlaceHolder(
                    photo!!.blurHash,
                    width = 200,
                    height = 200,
                    blurHash = this@ImagePreviewFragment.blurHash,
                    response = {
                        it.into(imageView)
                    })

            description.text =
                photo?.description ?: photo?.alternateDescription ?: photo?.user?.name ?: ""
            description.isSelected = true
            transparentView.setBackgroundColor(Color.parseColor(photo?.color ?: "#000000"))
            transparentView.alpha = 0.39215687F

        } ?: dismiss()

    }

    override fun onResume() {
        super.onResume()
        dialog?.window?.apply {
            val point = Point()
            this.windowManager.defaultDisplay.getSize(point)
            this.setGravity(Gravity.CENTER)
        }

    }
}