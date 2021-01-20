package com.pparreno.cameratrial.utils.files

import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.pparreno.cameratrial.R
import java.io.File
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*


class FileHelper {
    companion object {

        private val TAG = "FileHelper"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

        @JvmStatic
        fun getFileForStorage(outputDirectory: File): File {
            return File(
                outputDirectory,
                SimpleDateFormat(
                    FILENAME_FORMAT, Locale.US
                ).format(System.currentTimeMillis()) + ".jpg"
            )
        }

        @JvmStatic
        fun getOutputDirectory(activity: Activity): File {
            val mediaDir = activity.externalMediaDirs?.firstOrNull()?.let {
                File(it, activity.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else activity?.filesDir!!
        }

        @JvmStatic
        fun saveImage(bitmap: Bitmap, activity: Activity, uri: Uri){
            try {
                val outstream: OutputStream = activity.contentResolver.openOutputStream(uri)!!
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, outstream)
                outstream.close()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(
                    activity,
                    e.message,
                    Toast.LENGTH_LONG
                ).show()
                e.message?.let { Log.e(TAG, it) }
            }
        }
    }
}