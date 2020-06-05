package com.hb.utilities

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONException

object PopupUtils {

    /**
     * This method is used to show a message to user using dialog
     * @param activity
     * @param message - is a string to show to user
     * @return
     */

    fun showMessage(
        activity: AppCompatActivity,
        message: String?
    ) {
        val builder =
            AlertDialog.Builder(activity)
        try {
            builder.setMessage(message)
                .setCancelable(false)
                .setTitle(activity.resources.getString(R.string.app_name))
                .setPositiveButton(
                    "Ok",
                    DialogInterface.OnClickListener { dialog, id -> })
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        val alert = builder.create()
        alert.show()
    }

    /**
     * This method is used to show a message to user using Toast
     * @param activity
     * @param message - is a string to show to user
     * @return
     */

    fun showToastMessage(
        context: Context?,
        message: String?
    ) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}