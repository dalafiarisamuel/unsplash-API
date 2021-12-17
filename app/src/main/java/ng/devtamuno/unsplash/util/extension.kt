package ng.devtamuno.unsplash.util

import android.app.Activity
import android.content.Context
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun Activity.hideKeyboard() {
    val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val f = this.currentFocus
    if (null != f && null != f.windowToken && EditText::class.java.isAssignableFrom(f.javaClass)) {
        imm.hideSoftInputFromWindow(f.windowToken, 0)
    } else this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
}

operator fun Spannable.plus(other: Spannable): Spannable {
    return SpannableStringBuilder(this).append(other)
}

fun ViewGroup.inflate(layout: Int): View {
    val layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    return layoutInflater.inflate(layout, this, false)
}
