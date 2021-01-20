package com.pparreno.cameratrial.ui.profile.repository

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import com.pparreno.cameratrial.ui.profile.model.PictureItem
import java.io.File


class ImageRepository() {

    private var imageItems: MutableLiveData<ArrayList<PictureItem>> = MutableLiveData<ArrayList<PictureItem>>()

    init {
        imageItems.value = ArrayList()
    }

    fun loadSavedImages(srcDir: File) {
        imageItems.value!!.clear()
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
        val newItem = PictureItem(uri, "")
        imageItems.value?.add(newItem)
    }



}