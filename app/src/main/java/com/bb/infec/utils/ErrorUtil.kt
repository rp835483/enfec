package com.bb.infec.utils

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.bb.infec.R
import com.bb.infec.api.baseModel.ErrorBean
import com.bb.infec.extentions.getGsonInstance

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorUtil {
    fun handlerGeneralError(context: Context?, view: View?, throwable: Throwable) {
        // No need to pass the view. i'll remove later

        if (context == null) return

        when (throwable) {
            //For Display Toast

            is ConnectException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is SocketTimeoutException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is UnknownHostException -> Toast.makeText(
                context,
                "Unable to connect to the internet please try again",
                Toast.LENGTH_SHORT
            ).show()
            is InternalError -> Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show()

            is HttpException -> {
                try {
                    when (throwable.code()) {
                        401 -> displayError(context,throwable)
                        403 -> displayError(context, throwable)
                        400 -> displayError(context, throwable)
                        else -> displayError(context, throwable)
                    }
                } catch (exception: Exception) {

                }
            }
            else -> {
                Log.e("Other Exception", "Maybe wrong response model is set")
                Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun displayError(context: Context, exception: HttpException) {
        try {
            val errorBody = getGsonInstance().fromJson(
                exception.response()?.errorBody()?.charStream(),
                ErrorBean::class.java
            )
            Toast.makeText(context, errorBody.errorMessage, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("Error Utils", e.message + "")
            Toast.makeText(
                context, context.getString(R.string.error_exception), Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun displaySnackbarError(context: Context, view: View, exception: HttpException) {
        try {
            val errorBody = getGsonInstance().fromJson(
                exception.response()?.errorBody()?.charStream(),
                ErrorBean::class.java
            )

            val toast = Toast.makeText(
                context,
                errorBody.errorMessage.toString(),
                Toast.LENGTH_SHORT
            )
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                toast.view?.setBackgroundResource(R.drawable.ic_launcher_background)
//            }
            toast.view?.setBackgroundColor(context.resources.getColor(R.color.purple_200))
            val textView = toast.view?.findViewById<TextView>(R.id.message)
            textView?.setTextColor(context.resources.getColor(R.color.white))
            toast.show()

//            val snackbar = Snackbar.make(
//                view,
//                errorBody.errorMessage.toString(),
//                Snackbar.LENGTH_SHORT
//            )
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                snackbar.view.setBackgroundResource(R.drawable.button_bg_primary_snackbar)
//            }
//            val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
//            textView.setTextColor(context.resources.getColor(R.color.white))
//            snackbar.show()

//            Toast.makeText(context, errorBody.ErrorDetails, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("Error Utils", e.message + "")



            val toast = Toast.makeText(
                context,
                R.string.error_exception,
                Toast.LENGTH_SHORT
            )
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                toast.view?.setBackgroundResource(R.drawable.ic_launcher_background)
//            }
            toast.view?.setBackgroundColor(context.resources.getColor(R.color.purple_200))
            val textView = toast.view?.findViewById<TextView>(R.id.message)
            textView?.setTextColor(context.resources.getColor(R.color.white))
            toast.show()

//            val snackbar = Snackbar.make(
//                view,
//                R.string.error_exception,
//                Snackbar.LENGTH_SHORT
//            )
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                snackbar.view.setBackgroundResource(R.drawable.button_bg_primary_snackbar)
//            }
//            val textView = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
//            textView.setTextColor(context.resources.getColor(R.color.white))
//            snackbar.show()

//            Toast.makeText(
//                context, context.getString(R.string.error_exception), Toast.LENGTH_SHORT
//            ).show()
        }
    }

//    private fun forceLogout(view: View?, exception: HttpException) {
//        try {
//            val errorBody = getGsonInstance().fromJson(
//                exception.response()?.errorBody()?.charStream(),
//                ErrorBean::class.java
//            )
//            Log.e("ErrorMessage", errorBody.ErrorDetails + "")
//            Toast.makeText(view?.context, errorBody.ErrorDetails, Toast.LENGTH_SHORT).show()
//
//            val act = view?.getParentActivity()
//            act?.run {
//                val viewModelUser = ViewModelProvider(act).get(UserViewModel::class.java)
//                viewModelUser.deleteUserData()
//                startActivity(Intent(this, LoginActivity::class.java))
//                finishAffinity()
//            }
//        } catch (e: java.lang.Exception) {
//            Toast.makeText(
//                view?.context,
//                view?.context?.getString(R.string.error_exception),
//                Toast.LENGTH_SHORT
//            ).show()
//        }
//    }


}