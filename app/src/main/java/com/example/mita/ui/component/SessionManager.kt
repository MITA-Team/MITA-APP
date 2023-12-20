package com.example.mita.ui.component

import android.content.Context

object SessionManager {
    private const val PREF_NAME = "MyAppPreferences"
    private const val KEY_IS_LOGGED_IN = "isLoggedIn"

    fun setLoggedInStatus(context: Context, isLoggedIn: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
            apply()
        }
    }

    fun isLoggedIn(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(KEY_IS_LOGGED_IN, false)
    }
}
