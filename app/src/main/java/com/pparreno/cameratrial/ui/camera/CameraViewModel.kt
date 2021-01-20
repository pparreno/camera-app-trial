package com.pparreno.cameratrial.ui.camera


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CameraViewModel : ViewModel() {

    val takePhoto = MutableLiveData<Boolean>()

    fun shouldTakePhoto(item: Boolean) {
        takePhoto.value = item
    }
}