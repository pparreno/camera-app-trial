package com.pparreno.cameratrial.utils.files

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToInt


class UIHelper {

    companion object {
        @JvmStatic
        fun getIntFromDips(dips: Float, context: Context): Int {
            val r: Resources = context.resources
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dips, r.displayMetrics
            ).roundToInt()
        }
    }

}