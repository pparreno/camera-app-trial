package com.pparreno.cameratrial.ui.profile.repository

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.net.Uri
import com.pparreno.cameratrial.ui.profile.model.PictureItem
import java.io.File
import java.util.*
import kotlin.collections.ArrayList


class ImageRepository {
    var imageItems: ArrayList<PictureItem> = ArrayList()

    fun loadSavedImages(srcDir: File) {
        imageItems.clear()
        if (srcDir.exists()) {
            val files: Array<File> = srcDir.listFiles()
            for (file in files) {
                val absolutePath: String = file.absolutePath
                val extension = absolutePath.substring(absolutePath.lastIndexOf("."))
                if (extension == ".jpg") {
                    loadImage(file)
                }
            }
        }
    }

    private fun loadImage(file: File) {
        val uri = Uri.fromFile(file)
        val newItem = PictureItem(uri, getDateFromUri(uri))
        imageItems.add(newItem)
    }

    @SuppressLint("SimpleDateFormat")
    private fun getDateFromUri(uri: Uri): String {
        val split = uri.path!!.split("/").toTypedArray()
        val fileName = split[split.size - 1]
        val fileNameNoExt = fileName.split("\\.").toTypedArray()[0]
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(Date(fileNameNoExt.toLong()))
    }

}