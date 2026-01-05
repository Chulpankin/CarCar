package com.example.data.impl.firebase.user.datasource

import com.example.data.api.user.AuthService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
) : AuthService {

    override suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).await()
    }

    override fun isUserAuthorized(): Boolean = auth.currentUser != null

    override fun getCurrentUserId(): String? = auth.currentUser?.uid
}

