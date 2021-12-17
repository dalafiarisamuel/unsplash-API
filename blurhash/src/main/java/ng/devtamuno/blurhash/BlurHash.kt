package ng.devtamuno.blurhash

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.util.LruCache
import kotlinx.coroutines.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

const val LUR_SIZE = 10

class BlurHash(
    private var context: Context,
    lruSize: Int = LUR_SIZE,
    private var punch: Float = 1F
) {
    private var data: LruCache<String, BitmapDrawable> = LruCache(lruSize)
    private val scope = CoroutineScope(Dispatchers.Main)

    fun clean() {
        scope.cancel()
        data.evictAll()
    }

    fun execute(
        blurString: String,
        width: Int,
        height: Int,
        response: (drawable: BitmapDrawable) -> Unit
    ) {
        scope.launch {
            var blurBitmap = getBlurDrawable(blurString)
            withContext(Dispatchers.IO) {
                blurBitmap ?: run {
                    val bitmap = BlurHashDecoder.decode(
                        blurString,
                        width,
                        height,
                        punch,
                        useCache = false
                    )
                    blurBitmap = BitmapDrawable(
                        context.resources,
                        bitmap
                    )
                    cache(blurString, blurBitmap!!)
                }
            }
            response(blurBitmap!!)
        }
    }

    private fun cache(blurString: String, drawable: BitmapDrawable) {
        data.put(blurString, drawable)
    }

    private fun getBlurDrawable(blurString: String): BitmapDrawable? {
        return data.get(blurString)
    }
}

private fun Bitmap.compressBitmapV2(): Bitmap? {
    val baos = ByteArrayOutputStream()
    this.compress(
        Bitmap.CompressFormat.JPEG,
        50,
        baos
    )
    var options = 50
    while (baos.toByteArray().size / 1024 > 300) {
        baos.reset()
        this.compress(
            Bitmap.CompressFormat.JPEG,
            options,
            baos
        ) //
        options -= 10
    }
    val isBm =
        ByteArrayInputStream(baos.toByteArray())
    return BitmapFactory.decodeStream(isBm, null, null)
}