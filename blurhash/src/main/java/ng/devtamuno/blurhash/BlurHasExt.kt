package ng.devtamuno.blurhash

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.request.RequestOptions

// FOR GLIDE
@SuppressLint("CheckResult")
fun RequestBuilder<Drawable>.blurPlaceHolder(
    blurString: String?,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestBuilder: RequestBuilder<Drawable>) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString ?: "L6Pj0^i_.AyE_3t7t7R**0o#DgR4", width, height) { drawable ->
            this@blurPlaceHolder.placeholder(drawable)
            response(this@blurPlaceHolder)
        }
    }
}

@SuppressLint("CheckResult")
fun RequestBuilder<Bitmap>.blurPlaceHolderBitmap(
    blurString: String?,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestBuilder: RequestBuilder<Bitmap>) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString ?: "L6Pj0^i_.AyE_3t7t7R**0o#DgR4", width, height) { drawable ->
            this@blurPlaceHolderBitmap.placeholder(drawable)
            response(this@blurPlaceHolderBitmap)
        }
    }
}

fun RequestBuilder<Drawable>.blurPlaceHolder(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestBuilder: RequestBuilder<Drawable>) -> Unit
) {
    targetView.post {
        blurPlaceHolder(blurString, targetView.width, targetView.height, blurHash, response)
    }
}

@SuppressLint("CheckResult")
fun RequestOptions.blurPlaceHolderOf(
    blurString: String,
    width: Int = 0,
    height: Int = 0,
    blurHash: BlurHash,
    response: (requestOptions: RequestOptions) -> Unit
) {
    if (width != 0 && height != 0) {
        blurHash.execute(blurString, width, height) { drawable ->
            this@blurPlaceHolderOf.placeholder(drawable)
            response(this@blurPlaceHolderOf)
        }
    }
}

fun RequestOptions.blurPlaceHolderOf(
    blurString: String,
    targetView: View,
    blurHash: BlurHash,
    response: (requestOptions: RequestOptions) -> Unit
) {
    targetView.post {
        blurPlaceHolderOf(blurString, targetView.width, targetView.height, blurHash, response)
    }
}
