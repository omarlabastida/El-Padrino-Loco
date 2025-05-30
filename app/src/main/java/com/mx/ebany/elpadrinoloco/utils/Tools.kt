package com.mx.ebany.elpadrinoloco.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.io.ByteArrayOutputStream

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

        fun uriToBitmap(uri: Uri, context: Context): Bitmap {
            val inputStream = context.contentResolver.openInputStream(uri)
            return BitmapFactory.decodeStream(inputStream!!)
        }

        fun bitmapToBase64(bitmap: Bitmap): String {
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
            val byteArray = byteArrayOutputStream.toByteArray()
            return Base64.encodeToString(byteArray, Base64.NO_WRAP)
        }

    }
}