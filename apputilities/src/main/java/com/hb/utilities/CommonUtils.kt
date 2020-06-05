package com.hb.utilities

import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.json.JSONException

object CommonUtils {


    /**
     * This method is used to navigate the user to app in the playstore
     *
     * @param activity
     */

    fun navigateToPlayStore(activity: AppCompatActivity) {

        val builder =
            AlertDialog.Builder(activity)
        builder.setMessage("Please update to the latest version to continue using the Application!")
        builder.setPositiveButton(
            "Update"
        ) { dialog, which ->
            dialog.dismiss()
            val appPackageName = activity.packageName
            try {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (anfe: ActivityNotFoundException) {
                activity.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
            activity.finish()
        }
        val diag = builder.create()
        diag.setCancelable(false)
        diag.show()
    }

    /**
     * This method is used hide keyboard
     *
     * @param activity
     */
    fun hideKeyboard(activity: Activity) {
        try {
            val inputManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                activity.currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        } catch (e: Exception) {
        }
    }

    /**
     * This method is used show keyboard
     *
     * @param activity
     */
    fun showKeyboard(activity: Activity) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(
            activity.currentFocus!!.windowToken,
            InputMethodManager.SHOW_FORCED,
            0
        )
    }

    /**
     * This method is used show keyboard
     *
     * @param activity
     */
    fun showKeyboard(activity: Activity, view: View) {
        val inputMethodManager =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInputFromWindow(
            view.windowToken,
            InputMethodManager.SHOW_FORCED,
            0
        )
    }


    /**
     * This method is used hide keyboard
     *
     * @param activity
     */
    fun hideKeyboard(activity: Activity?, view: View?) {
        try {
            if (activity != null && view != null) {
                val inputManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (e: Exception) {
        }
    }


    /**
     * This method is used to get the status bar height
     *
     * @param activity
     */

    fun getStatusBarHeight(activity: Activity): Int {
        val rectangle = Rect()
        val window = activity.window
        window.decorView.getWindowVisibleDisplayFrame(rectangle)
        val statusBarHeight = rectangle.top
        val contentViewTop =
            window.findViewById<View>(Window.ID_ANDROID_CONTENT).top
        val titleBarHeight = contentViewTop - statusBarHeight
        return statusBarHeight
    }


    /**
     * This method is used to convert dp into pixel
     * @param sizeInDp - Is a value which we want to convert into pixel
     * @param context
     * @return
     */

    fun convertDpToPixel(
        sizeInDp: Int,
        context: Context
    ): Float {
        val dip = sizeInDp.toFloat()
        val r = context.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            r.displayMetrics
        )
    }

    /**
     * Return device name as HTC One (M8)
     *
     * @return
     */
    fun getDeviceName(): String? {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            StringUtils.capitalize(model)
        } else {
            StringUtils.capitalize(manufacturer) + " " + model
        }
    }

    /**
     * Return Random Instant Value
     *
     * @return
     */

    fun getRandomInstantValue(): Int {
        return Math.abs(System.currentTimeMillis().toInt())
    }


    /**
     * This method is used to check permission granted or not
     *
     * @param activity
     * @param permission - permission which we required to check is granted or not
     * @return
     */

    fun isPermissionGranted(activity: Activity?, permission: String?): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true
        }

        //Getting the permission status
        val result = ContextCompat.checkSelfPermission(activity!!, permission!!)

        //If permission is granted returning true
        return result == PackageManager.PERMISSION_GRANTED
    }


    /**
     * This method is used to share string or text using Intent
     * @param context
     * @param shareText - is a string to share
     * @return
     */

    fun shareTextIntent(
        context: Context,
        shareText: String?
    ) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareText)
        try {
            context.startActivity(
                Intent.createChooser(
                    sharingIntent,
                    "Share Via"
                )
            )
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}