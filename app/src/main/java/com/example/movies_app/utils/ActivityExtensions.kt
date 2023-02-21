package com.example.movies_app.utils

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.example.movies_app.R

fun Activity.showAlertDialog(message: String, positiveButtonAction: (() -> Unit)? = null){
    AlertDialog.Builder(this)
        .setMessage(message)
        .setPositiveButton(R.string.error_dialog_positive_button_message){ _, _ ->
            positiveButtonAction?.invoke()
        }
        .setNegativeButton(R.string.error_dialog_negative_button_message){ _, _ -> }
        .show()
}