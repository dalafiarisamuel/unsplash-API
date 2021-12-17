package ng.devtamuno.unsplash.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UnsplashResponse(
    val results: List<UnsplashPhoto>
)