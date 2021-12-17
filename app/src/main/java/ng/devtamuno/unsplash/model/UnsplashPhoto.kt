package ng.devtamuno.unsplash.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class UnsplashPhoto(
    val id: String,
    @Json(name = "blur_hash")
    val blurHash: String?,
    val width: Int,
    val height: Int,
    val color: String,
    @Json(name = "alt_description")
    val alternateDescription: String?,
    val description: String?,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) : Parcelable {

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String
    ) : Parcelable

    @JsonClass(generateAdapter = true)
    @Parcelize
    data class UnsplashUser(
        val name: String,
        val username: String
    ) : Parcelable {
        val attributionUrl get() = "https://unsplash.com/$username?utm_source=ImageLoader&utm_medium=referral"
    }
}