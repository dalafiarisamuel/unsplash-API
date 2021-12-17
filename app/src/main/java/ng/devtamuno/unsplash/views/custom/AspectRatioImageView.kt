package ng.devtamuno.unsplash.views.custom


import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import ng.devtamuno.unsplash.R


class AspectRatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ShapeableImageView(context, attrs, defStyleAttr) {

    var aspectRatio: Double = -1.0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (aspectRatio == -1.0) return
        val width = measuredWidth
        val height = (width * aspectRatio).toInt()
        if (height == measuredHeight) return
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        shapeAppearanceModel = shapeAppearanceModel
            .toBuilder()
            .setAllCorners(CornerFamily.ROUNDED, resources.getDimension(R.dimen.corner_radius))
            .build()
    }
}