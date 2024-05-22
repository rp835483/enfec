package com.bb.infec.utils

import android.content.Context
import android.content.SharedPreferences

open class PreferenceManager protected constructor(context: Context) {
    private val preference: SharedPreferences =
        context.getSharedPreferences("Test", Context.MODE_PRIVATE)

    companion object {
        private var preferenceManager: PreferenceManager? = null
        private const val LOGIN = "LOGIN"
        private const val TOKEN = "TOKEN"
        private const val EMAIL = "EMAIL"
        private const val USERNAME = "USERNAME"
        private const val FIRSTNAME = "FIRSTNAME"
        private const val LASTNAME = "LASTNAME"
        private const val USERID = "USERID"
        private const val PHONE = "PHONE"
        private const val Mobile = "Mobile"
        private const val Status = "Status"
        private const val ProfileImage = "ProfileImage"

        fun getInstance(context: Context): PreferenceManager {
            if (preferenceManager == null)
                preferenceManager =
                    PreferenceManager(context)
            return preferenceManager as PreferenceManager
        }
    }

    fun setLogin(login: Boolean) {
        preference.edit().putBoolean(LOGIN, login).apply()
    }

    val isLoggedIn: Boolean
        get() = preference.getBoolean(LOGIN, false)

    fun setAuthToken(authToken: String) {
        preference.edit().putString(TOKEN, authToken).apply()
    }

    val getAuthToken: String
        get() = preference.getString(TOKEN, "").toString()

    fun setEmail(email: String) {
        preference.edit().putString(EMAIL, email).apply()
    }

    val getEmail: String
        get() = preference.getString(EMAIL, "").toString()


    fun setProfileImage(profileimage: String) {
        preference.edit().putString(ProfileImage, profileimage).apply()
    }

    val getProfileImage: String
        get() = preference.getString(ProfileImage, "").toString()



    fun setMobiileNo(username: String) {
        preference.edit().putString(Mobile, username).apply()
    }

    val getMobiileNo: String
        get() = preference.getString(Mobile, "").toString()



    fun setUserName(username: String) {
        preference.edit().putString(USERNAME, username).apply()
    }
    val getUserName: String
        get() = preference.getString(USERNAME, "").toString()


      fun setFirstName(username: String) {
        preference.edit().putString(FIRSTNAME, username).apply()
    }
    val getFirstName: String
        get() = preference.getString(FIRSTNAME, "").toString()


      fun setLastName(username: String) {
        preference.edit().putString(LASTNAME, username).apply()
    }
    val getLastName: String
        get() = preference.getString(LASTNAME, "").toString()




    fun setPhone(phone: String) {
        preference.edit().putString(PHONE, phone.toString()).apply()
    }

    val getPhone: String
        get() = preference.getString(PHONE, "").toString()





    fun setStatus(phone: String) {
        preference.edit().putString(Status, phone.toString()).apply()
    }

    val getStatus: String
        get() = preference.getString(Status, "").toString()

    fun setUserId(userId: Int) {
        preference.edit().putInt(USERID, userId).apply()
    }

    val getUserId: Int
        get() = preference.getInt(USERID, 0)

    open fun clear() {
        preference.edit().clear().apply()
    }

}