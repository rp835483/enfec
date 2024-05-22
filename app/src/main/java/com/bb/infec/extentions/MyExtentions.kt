package com.bb.infec.extentions

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.bb.infec.R
import com.bb.infec.api.baseModel.ErrorBean
import com.bb.infec.base.dialog.ProgressDialog
import com.bb.infec.utils.PreferenceManager
import com.bb.infec.base.callback.AlertDialogCallback
import com.bb.infec.base.callback.ListDialogCallback
import retrofit2.HttpException
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


// To get Gson Instance
var gson: Gson? = null

fun getGsonInstance(): Gson {

    if (gson == null)
        gson = Gson()
    return gson!!
}


fun getSha1Hex(clearString: String): String {
    val HEX_CHARS = "0123456789ABCDEF"
    val bytes = MessageDigest
        .getInstance("SHA-1")
        .digest(clearString.toByteArray())
    val result = StringBuilder(bytes.size * 2)
    bytes.forEach {
        val i = it.toInt()
        result.append(HEX_CHARS[i shr 4 and 0x0f])
        result.append(HEX_CHARS[i and 0x0f])
    }
    return result.toString()
}


fun mGetErrorMessage(exception: HttpException): String {
    var str = ""
    str = try {
        val errorBody: ErrorBean = getGsonInstance().fromJson(
            exception.response()?.errorBody()?.charStream(),
            ErrorBean::class.java
        )
        errorBody.errorMessage
    } catch (e: java.lang.Exception) {
        "Some Exception Occurred"
    }
    return str
}

private var progressDialog: ProgressDialog? = null

fun showProgressDialog(context: Context, message: String?) {
    if (!context.getParentActivity()?.isFinishing!! && !context.getParentActivity()?.isDestroyed!!) {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
        }
        progressDialog!!.setTitle("Please Wait")
        progressDialog!!.setMessage(message)
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }
}


fun Activity.showProgressDialog(context: Context, message: String?) {
    if (!isFinishing && !isDestroyed) {
        progressDialog = ProgressDialog(context)
        progressDialog?.let {
            it.setTitle("Please Wait")
            it.setMessage(message)
            it.setCancelable(false)
            it.show()
        }
    }
}

fun Activity.hideProgressDialog() {
    if (!isFinishing && !isDestroyed) {
        if (progressDialog != null)
            progressDialog!!.dismiss()
    }
}


fun hideProgressDialog(context: Context) {
    if (!context.getParentActivity()?.isFinishing!! && !context.getParentActivity()?.isDestroyed!!) {
        progressDialog?.dismiss()
    }
}

fun FragmentActivity.showListDialog(
    list: List<String?>,
    dialogId: Int,
    callback: ListDialogCallback
) {
    if (this.isFinishing && !this.isDestroyed) {
        val dialogAdapter: ArrayAdapter<String?> =
            ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, list)
        val dialogBuilder =
            AlertDialog.Builder(this)
                .setAdapter(
                    dialogAdapter
                ) { _: DialogInterface?, which: Int ->
                    callback.onItemSelected(
                        which,
                        list[which],
                        dialogId
                    )
                }
        dialogBuilder.setTitle(null)
        val alertDialog = dialogBuilder.create()
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertDialog.setOnDismissListener {
            callback.onDismiss(
                dialogId
            )
        }
        alertDialog.show()
    }
}

fun FragmentActivity.showListDialog(
    list: List<String?>,
    title: String?,
    dialogId: Int,
    callback: ListDialogCallback
) {
    if (!this.isFinishing && !this.isDestroyed) {
        val dialogAdapter: ArrayAdapter<String?> =
            ArrayAdapter(this, android.R.layout.select_dialog_singlechoice, list)
        val dialogBuilder =
            AlertDialog.Builder(this)
                .setAdapter(
                    dialogAdapter
                ) { _: DialogInterface?, which: Int ->
                    callback.onItemSelected(
                        which,
                        list[which],
                        dialogId
                    )
                }
        dialogBuilder.setTitle(null)
        val alertDialog = dialogBuilder.create()
        alertDialog.setTitle(title)
        alertDialog.setOnDismissListener {
            callback.onDismiss(
                dialogId
            )
        }
        alertDialog.show()
    }
}

fun FragmentActivity.showAlert(
    title: String,
    message: String,
    dialogId: Int,
    callback: AlertDialogCallback
) {
    if (!this.isFinishing && !this.isDestroyed) {
        AlertDialog.Builder(this)
            .setTitle(if (title.isNotEmpty()) title else null)
            .setMessage(if (message.isNotEmpty()) message else null)
            .setCancelable(false)
            .setPositiveButton(
                "ok"
            ) { _: DialogInterface?, _: Int ->
                callback.onPositiveButton(
                    dialogId
                )
            }
            .setNegativeButton(
                "Cancel"
            ) { _: DialogInterface?, _: Int ->
                callback.onNegativeButton(
                    dialogId
                )
            }
            .create()
            .show()
    }
}

fun FragmentActivity.showAlert(
    title: String,
    message: String,
    dialogId: Int,
    positiveButton: String?,
    callback: AlertDialogCallback
) {
    if (!this.isFinishing && !this.isDestroyed) {
        AlertDialog.Builder(this)
            .setTitle(if (title.isNotEmpty()) title else null)
            .setMessage(if (message.isNotEmpty()) message else null)
            .setCancelable(false)
            .setNegativeButton(
                positiveButton
            ) { _: DialogInterface?, _: Int ->
                callback.onPositiveButton(
                    dialogId
                )
            }
            .create()
            .show()
    }
}

fun FragmentActivity.showAlert(title: String, message: String) {
    if (!this.isFinishing && !this.isDestroyed) {
        AlertDialog.Builder(this)
            .setTitle(if (title.isNotEmpty()) title else null)
            .setMessage(if (message.isNotEmpty()) message else null)
            .setCancelable(false)
            .setPositiveButton("OK", null)
            .create()
            .show()
    }
}

fun View.showSnackBar(message: String?) {
    if (!context.getParentActivity()?.isFinishing!! && !context.getParentActivity()?.isDestroyed!!) {
        val snackbar = Snackbar.make(
            this,
            message!!,
            Snackbar.LENGTH_SHORT
        )
        snackbar.view.setBackgroundColor(resources.getColor(R.color.white))
        val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
        textView.setTextColor(resources.getColor(R.color.black))
        snackbar.show()
    }
}


fun FragmentActivity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}


fun getPreferences(context: Context): PreferenceManager {
    return PreferenceManager.getInstance(context)
}


@SuppressLint("InflateParams")
fun encryptPassword(password: String): String {
    return try {
        // getInstance() method is called with algorithm SHA-1
        val md = MessageDigest.getInstance("SHA-1")

        // digest() method is called
        // to calculate message digest of the input string
        // returned as array of byte
        val messageDigest = md.digest(password.toByteArray())

        // Convert byte array into signum representation
        val no = BigInteger(1, messageDigest)

        // Convert message digest into hex value
        var hashtext: String = no.toString(16)

        // Add preceding 0s to make it 32 bit
        while (hashtext.length < 32) {
            hashtext = "0$hashtext"
        }

        // return the HashText
        hashtext
    } // For specifying wrong message digest algorithms
    catch (e: NoSuchAlgorithmException) {
        throw RuntimeException(e)
    }
    var hambaDialog:ProgressDialog?=null

    fun Activity.showLoader(context: Context) {
        if (!isFinishing && !isDestroyed) {
            if (hambaDialog == null) {
                hambaDialog = ProgressDialog(context)
            }
            hambaDialog?.let {
                it.setCancelable(false)
                it.show()
            }
        }
    }

    fun Activity.hideLoader() {
        if (!isFinishing && !isDestroyed) {
            if (hambaDialog != null)
                hambaDialog!!.dismiss()
        }
    }

    fun Activity.toggleLoader(context: Context, flag: Boolean) {
        try {
            if (flag) {
                showLoader(context)
            } else {
                hideLoader()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun Fragment.toggleLoader(context: Context, flag: Boolean) {
        try {
            if (flag) {
                context.getParentActivity()?.showLoader(context)
            } else {
                context.getParentActivity()?.hideLoader()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



}