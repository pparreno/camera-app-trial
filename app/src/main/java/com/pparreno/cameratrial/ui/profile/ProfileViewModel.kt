package com.pparreno.cameratrial.ui.profile

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pparreno.cameratrial.ui.profile.model.PictureItem
import com.pparreno.cameratrial.ui.profile.repository.ImageRepository
import com.pparreno.cameratrial.utils.files.FileHelper

class ProfileViewModel : ViewModel() {

    private val imageRepository : ImageRepository = ImageRepository()
    var imageItems: MutableLiveData<ArrayList<PictureItem>> = MutableLiveData<ArrayList<PictureItem>>()

    fun initializeObservableData(){
        imageItems = MutableLiveData<ArrayList<PictureItem>>()
        imageItems.value = ArrayList()
    }
    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner){
        imageRepository.imageItems.observe(lifecycleOwner, androidx.lifecycle.Observer {
            this.imageItems.value = it
        })
    }

    fun retrieveImages(activity: Activity){
        imageRepository.loadSavedImages(FileHelper.getOutputDirectory(activity))
    }


}