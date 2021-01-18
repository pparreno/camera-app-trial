package com.pparreno.cameratrial.utils.files

import android.app.Activity
import android.content.Context
import com.pparreno.cameratrial.R
import com.pparreno.cameratrial.ui.camera.CameraFragment
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class FileHelper {
    companion object {

        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"

        @JvmStatic
        fun getFileForStorage(outputDirectory:File): File {
            return File(
                outputDirectory,
                SimpleDateFormat(
                    FILENAME_FORMAT, Locale.US
                ).format(System.currentTimeMillis()) + ".jpg"
            )
        }

        @JvmStatic
        fun getOutputDirectory(activity : Activity): File {
            val mediaDir = activity.externalMediaDirs?.firstOrNull()?.let {
                File(it, activity.getString(R.string.app_name)).apply { mkdirs() }
            }
            return if (mediaDir != null && mediaDir.exists())
                mediaDir else activity?.filesDir!!
        }
    }
}