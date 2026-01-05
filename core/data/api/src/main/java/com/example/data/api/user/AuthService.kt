package com.example.data.api.user

interface AuthService {

    suspend fun signIn(email: String, password: String)

    suspend fun signUp(email: String, password: String)

    fun isUserAuthorized(): Boolean

    fun getCurrentUserId(): String?
}

