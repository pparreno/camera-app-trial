package com.pparreno.cameratrial.ui.photoeditor

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.pparreno.cameratrial.databinding.ActivityImageAdjustmentBinding
import com.pparreno.cameratrial.ui.camera.CameraFragment

class ImageAdjustmentActivity : AppCompatActivity() {

    lateinit var saturationSeekBar: SeekBar
    lateinit var contrastSeekBar: SeekBar
    lateinit var brightnessSeekBar: SeekBar
    lateinit var warmthSeekBar: SeekBar

    private var binding: ActivityImageAdjustmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageAdjustmentBinding.inflate(layoutInflater)
        val view: View = binding!!.getRoot()
        setContentView(view)

        binding!!.seekbarSaturation.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //sets the saturation of the image; 0 = grayscale, 1 = original, 2 = hyper saturated
                binding!!.imageFilterView.saturation = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding!!.seekbarContrast.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //This sets the contrast. 1 = unchanged, 0 = gray, 2 = high contrast
                binding!!.imageFilterView.contrast = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding!!.seekbarBrightness.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //sets the brightness of the image; 0 = black, 1 = original, 2 = twice as bright
                binding!!.imageFilterView.brightness = progress.toFloat()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        binding!!.seekbarWarmth.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 1 is neutral, 2 is warm, .5 is cold
                val warmthVal = progress/3.0f
                binding!!.imageFilterView.warmth = warmthVal + 0.5f
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        val dataURI = intent.getParcelableExtra<Uri>(CameraFragment.KEY_EXTRA_RESULT_URI)
        binding!!.imageFilterView.setImageURI(dataURI)
    }
}