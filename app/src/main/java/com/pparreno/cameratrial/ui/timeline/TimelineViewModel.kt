package com.pparreno.cameratrial.ui.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TimelineViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Timeline Fragment"
    }
    val text: LiveData<String> = _text
}