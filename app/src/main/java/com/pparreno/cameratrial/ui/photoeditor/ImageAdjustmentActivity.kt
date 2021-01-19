package com.pparreno.cameratrial.ui.photoeditor

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.pparreno.cameratrial.R
import com.pparreno.cameratrial.databinding.ActivityImageAdjustmentBinding
import com.pparreno.cameratrial.ui.camera.CameraFragment
import kotlinx.android.synthetic.main.activity_image_adjustment.view.*


class ImageAdjustmentActivity : AppCompatActivity() {

    private var binding: ActivityImageAdjustmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageAdjustmentBinding.inflate(layoutInflater)
        val view: View = binding!!.getRoot()
        setContentView(view)

        val labelSaturation = binding!!.labelSaturation
        val seekBarSaturation = binding!!.seekbarSaturation
        labelSaturation.text = String.format(
            "%s (%.2f)",
            getString(R.string.saturation),
            seekBarSaturation.progress / 50.0f
        )
        seekBarSaturation.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //sets the saturation of the image; 0 = grayscale, 1 = original, 2 = hyper saturated
                val saturationVal = progress / 50.0f
                binding!!.imageFilterView.saturation = saturationVal
                labelSaturation.text = String.format(
                    "%s (%.2f)",
                    getString(R.string.saturation),
                    saturationVal
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        val labelContrast = binding!!.labelContrast
        val seekBarContrast = binding!!.seekbarContrast
        labelContrast.text = String.format(
            "%s (%.2f)",
            getString(R.string.contrast),
            seekBarContrast.progress / 50.0f
        )
        seekBarContrast.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //This sets the contrast. 1 = unchanged, 0 = gray, 2 = high contrast
                val contrastVal = progress / 50.0f
                binding!!.imageFilterView.contrast = contrastVal
                labelContrast.text = String.format(
                    "%s (%.2f)",
                    getString(R.string.contrast),
                    contrastVal
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        val labelBrightness = binding!!.labelBrightness
        val seekBarBrightness = binding!!.seekbarBrightness
        labelBrightness.text = String.format(
            "%s (%.2f)",
            getString(R.string.brightness),
            seekBarBrightness.progress / 50.0f
        )
        seekBarBrightness.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //sets the brightness of the image; 0 = black, 1 = original, 2 = twice as bright
                val brightnessVal = progress / 50.0f
                binding!!.imageFilterView.brightness = brightnessVal
                labelBrightness.text = String.format(
                    "%s (%.2f)",
                    getString(R.string.brightness),
                    brightnessVal
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        val labelWarmth = binding!!.labelWarmth
        val seekBarWarmth = binding!!.seekbarWarmth
        labelWarmth.text = String.format(
            "%s (%.2f)",
            getString(R.string.warmth),
            (seekBarWarmth.progress / 90.0f) + 0.5f
        )
        seekBarWarmth.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // 1 is neutral, 2 is warm, .5 is cold
                val warmthVal = (progress / 90.0f) + 0.5f
                binding!!.imageFilterView.warmth = warmthVal
                labelWarmth.text = String.format(
                    "%s (%.2f)",
                    getString(R.string.warmth),
                    warmthVal
                )
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        val dataURI = intent.getParcelableExtra<Uri>(CameraFragment.KEY_EXTRA_RESULT_URI)
        binding!!.imageFilterView.setImageURI(dataURI)

        title = "";
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.image_adjustment_menu, menu)
        return true
    }
}
