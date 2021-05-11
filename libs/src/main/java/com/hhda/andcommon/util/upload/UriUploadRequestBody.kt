package com.hhda.andcommon.util.upload


import android.content.ContentResolver
import android.content.res.AssetFileDescriptor
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import okio.source
import java.io.IOException


/**
 * https://github.com/carlonzo/OkHttpWithContentUri/blob/patch-1/app/src/main/java/de/cketti/demo/okhttp/ContentUriRequestBody.kt
 * uri类型文件上传
 */
class UriUploadRequestBody(
    private val contentResolver: ContentResolver,
    private val contentUri: Uri
) : RequestBody() {

    override fun contentType(): MediaType? {
        val contentType = contentResolver.getType(contentUri) ?: return null
        return contentType.toMediaTypeOrNull()
    }

    override fun writeTo(sink: BufferedSink) {
        val inputStream = contentResolver.openInputStream(contentUri)
            ?: throw IOException("Couldn't open content URI for reading: $contentUri")

        inputStream.source().use { source ->
            sink.writeAll(source)
        }
    }

    override fun contentLength(): Long {
        val fileDescriptor = kotlin.runCatching {
            contentResolver.openAssetFileDescriptor(contentUri, "r")
        }.getOrNull() ?: return super.contentLength()

        val size = fileDescriptor.use { it.length }
        return if (size == AssetFileDescriptor.UNKNOWN_LENGTH) {
            super.contentLength()
        } else {
            size
        }
    }
}