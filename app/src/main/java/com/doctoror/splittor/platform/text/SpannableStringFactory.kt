package com.doctoror.splittor.platform.text

import android.text.SpannableString

/**
 * Convenience to not rely on androidTest whenever SpannableString is involved
 */
class SpannableStringFactory {

    fun create(source: CharSequence) = SpannableString(source)
}
