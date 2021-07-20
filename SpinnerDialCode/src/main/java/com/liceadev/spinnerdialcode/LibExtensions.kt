package com.liceadev.spinnerdialcode

import android.content.Context

fun Context.drawableFromString(fileName: String): Int {
    return resources.getIdentifier(fileName, "drawable", packageName)
}