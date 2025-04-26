package com.mx.ebany.elpadrinoloco.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Tools {

    companion object{
        fun setSizeCardRecyclerView(list: List<Any>, rv: RecyclerView) {
            if (list.isNotEmpty()) {
                val layoutParams = rv.layoutParams
                layoutParams.height = list.count() * ViewGroup.LayoutParams.WRAP_CONTENT
                rv.layoutParams = layoutParams
                rv.isNestedScrollingEnabled = false
            }
        }

        fun base64ToBitmap(base64String: String): Bitmap? {
            return try {
                val decodedBytes = Base64.decode(base64String, Base64.DEFAULT)
                BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}