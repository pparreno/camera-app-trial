package com.pparreno.cameratrial.ui.photoeditor

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.drawToBitmap
import com.pparreno.cameratrial.R
import com.pparreno.cameratrial.databinding.ActivityImageAdjustmentBinding
import com.pparreno.cameratrial.ui.camera.CameraFragment
import com.pparreno.cameratrial.utils.files.FileHelper
import kotlinx.android.synthetic.main.activity_image_adjustment.*
import kotlinx.android.synthetic.main.activity_image_adjustment.view.*


class ImageAdjustmentActivity : AppCompatActivity() {

    private var binding: ActivityImageAdjustmentBinding? = null

    lateinit var seekBarSaturation : SeekBar
    lateinit var seekBarContrast : SeekBar
    lateinit var seekBarBrightness : SeekBar
    lateinit var seekBarWarmth : SeekBar

    private val TAG = "ImageAdjustmentActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageAdjustmentBinding.inflate(layoutInflater)
        val view: View = binding!!.getRoot()
        setContentView(view)

        val labelSaturation = binding!!.labelSaturation
        seekBarSaturation = binding!!.seekbarSaturation
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
        seekBarContrast = binding!!.seekbarContrast
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
        seekBarBrightness = binding!!.seekbarBrightness
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
        seekBarWarmth = binding!!.seekbarWarmth
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.menu_item_undo -> resetSliderValues()
            R.id.menu_item_save -> saveImage()
        }

        return true;
    }

    private fun saveImage() {
        val dialogBuilder = AlertDialog.Builder(this)

        // set message of alert dialog
        dialogBuilder.setMessage(R.string.save_image_dialog_message)
            // if the dialog is cancelable
            .setCancelable(false)
            // positive button text and action
            .setPositiveButton(R.string.save, DialogInterface.OnClickListener {
                    dialog, id ->
                run {
                    Log.d(TAG,"Attempting to save file")
                    val rect = RectF()
                    imageFilterView.imageMatrix.mapRect(rect, RectF(imageFilterView.drawable.bounds))
                    val bm = Bitmap.createBitmap(
                        imageFilterView.drawToBitmap(),
                        rect.left.toInt(),
                        rect.top.toInt(),
                        rect.width().toInt(),
                        rect.height().toInt()
                    )
                    val outputFile = FileHelper.saveImage(bm, this@ImageAdjustmentActivity)
                    val editImageIntent = Intent(this@ImageAdjustmentActivity, EditImageKActivity::class.java)
                    editImageIntent.putExtra(CameraFragment.KEY_EXTRA_RESULT_URI, outputFile)
                    this@ImageAdjustmentActivity.startActivity(editImageIntent)
                }
            })
            // negative button text and action
            .setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })

        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle(R.string.save_dialog_title)
        // show alert dialog
        alert.show()
    }

    private fun resetSliderValues() {
        seekBarWarmth.progress = 40
        seekBarContrast.progress = 50
        seekBarBrightness.progress= 50
        seekBarSaturation.progress = 50
    }
}
