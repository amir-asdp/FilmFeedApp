package com.example.data.source.local

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.net.URL
import kotlin.coroutines.CoroutineContext

class ImageCacheManager(private val context: Context, private val coroutineContext: CoroutineContext) {

    suspend fun cacheImage(imageUrl: String): String {
        return withContext(coroutineContext) {
            try {
                val url = URL(imageUrl)
                val input = url.openStream()
                val bitmap = BitmapFactory.decodeStream(input)
                val filename = Uri.parse(imageUrl).lastPathSegment ?: "default.jpg"
                val file = File(context.cacheDir, filename)
                FileOutputStream(file).use { out ->
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
                }
                file.absolutePath
            }
            catch (e: IOException) {
                e.printStackTrace()
                ""
            }
        }
    }

}