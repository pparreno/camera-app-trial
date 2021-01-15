package com.pparreno.cameratrial.ui.camera

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.pparreno.cameratrial.R

class PhotoEditorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val root = inflater.inflate(R.layout.fragment_photoeditor, container, false)
        return root
    }

}