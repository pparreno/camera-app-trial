package com.pparreno.cameratrial.ui.profile

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.pparreno.cameratrial.ui.profile.repository.ImageRepository
import com.pparreno.cameratrial.utils.files.FileHelper

class ProfileViewModel : ViewModel() {

    private val imageRepository : ImageRepository = ImageRepository()

    public fun retrieveImages(activity: Activity){

        imageRepository.loadSavedImages(FileHelper.getOutputDirectory(activity))
    }


}