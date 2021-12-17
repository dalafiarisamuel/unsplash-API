package ng.devtamuno.unsplash.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import ng.devtamuno.blurhash.BlurHash
import ng.devtamuno.blurhash.blurPlaceHolder
import ng.devtamuno.unsplash.R
import ng.devtamuno.unsplash.model.UnsplashPhoto
import ng.devtamuno.unsplash.views.custom.AspectRatioImageView

class ImageAdapter(private val blurHash: BlurHash, private val listener: OnItemClickListener) :
    PagingDataAdapter<UnsplashPhoto, ImageAdapter.PhotoViewHolder>(PHOTO_COMPARATOR) {

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder =
        PhotoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.image_item_view, parent, false)
        )

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val aspectImage: AspectRatioImageView =
            itemView.findViewById(R.id.aspectRatioImageView)

        fun bind(position: Int) {
            getItem(position)?.also { currentPhoto ->
                aspectImage.aspectRatio =
                    currentPhoto.height.toDouble() / currentPhoto.width.toDouble()

                Glide.with(aspectImage)
                    .load(currentPhoto.urls.small)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .blurPlaceHolder(
                        currentPhoto.blurHash,
                        width = 200,
                        height = 200,
                        blurHash = blurHash,
                        response = {
                            it.into(aspectImage)
                        })


                aspectImage.setOnClickListener {
                    listener.onItemClick(currentPhoto)
                }

                aspectImage.setOnLongClickListener {
                    listener.onItemLongClicked(currentPhoto)
                    true
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: UnsplashPhoto?)
        fun onItemLongClicked(photo: UnsplashPhoto?)
    }

    companion object {
        private val PHOTO_COMPARATOR = object : DiffUtil.ItemCallback<UnsplashPhoto>() {
            override fun areItemsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UnsplashPhoto, newItem: UnsplashPhoto) =
                oldItem == newItem
        }
    }
}