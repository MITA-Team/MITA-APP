package com.example.mita.data.repo

import com.example.mita.data.api.ApiService
import com.example.mita.data.pref.UserPreference
import com.example.mita.utils.await
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class AuthRepository(
    private val userPreference: UserPreference,
    private val apiService: ApiService

) {

    suspend fun loginWithGoogle(idToken: String): Unit = withContext(IO) {
        Firebase.auth
            .signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
            .await()
    }

    suspend fun signUpWithEmail(email: String, password: String): Unit = withContext(IO) {
        Firebase.auth.createUserWithEmailAndPassword(email, password).await()
    }

    suspend fun signInWithEmail(email: String, password: String): Unit = withContext(IO) {
        Firebase.auth.signInWithEmailAndPassword(email, password).await()
    }

    suspend fun sendPasswordResetEmail(email: String): Unit = withContext(IO) {
        Firebase.auth.sendPasswordResetEmail(email).await()
    }

    suspend fun signInWithPhoneNumber(credential: AuthCredential): Unit = withContext(IO) {
        Firebase.auth.signInWithCredential(credential).await()
    }



    companion object {
        @Volatile
        var INSTANCE: AuthRepository? = null

        fun getInstance(
            userPreference: UserPreference,
            apiService: ApiService
        ) = INSTANCE ?: synchronized(this) {
            val instance = AuthRepository(userPreference, apiService)
            INSTANCE = instance
            return instance
        }
    }
}