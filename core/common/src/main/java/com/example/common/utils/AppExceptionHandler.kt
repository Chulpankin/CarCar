package com.example.common.utils

import com.example.common.R
import com.example.common.exceptions.AppException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.firestore.FirebaseFirestoreException
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject

class AppExceptionHandler @Inject constructor(
    private val resourceManager: ResourceManager
) {

    fun handleException(exception: Throwable): AppException =
        when (exception) {
            is FirebaseAuthException -> handleAuthException(exception)
            is FirebaseFirestoreException -> handleFirestoreException(exception)
            is FirebaseException -> handleGeneralFirebaseException()
            else -> if (isNetworkException(exception)) handleNetworkException(exception)
            else {
                val httpResponse = getHttpResponse(exception)
                if (httpResponse != null) handleHttpException(httpResponse, exception)
                else handleGeneralException()
            }
        }

    private fun handleAuthException(exception: FirebaseAuthException): AppException =
        when (exception) {
            is FirebaseAuthInvalidCredentialsException ->
                AppException.AuthInvalidCredentialsException(
                    resourceManager.getString(R.string.error_auth_invalid_credentials)
                )
            is FirebaseAuthInvalidUserException ->
                AppException.AuthUserDisabledException(resourceManager.getString(R.string.error_auth_user_disabled))
            else -> AppException.AuthUnknownException(resourceManager.getString(R.string.error_auth_unknown))
        }

    private fun handleFirestoreException(exception: FirebaseFirestoreException): AppException =
        when (exception.code) {
            FirebaseFirestoreException.Code.PERMISSION_DENIED ->
                AppException.FirestorePermissionDeniedException(
                    resourceManager.getString(R.string.error_firestore_permission_denied)
                )
            FirebaseFirestoreException.Code.UNAVAILABLE ->
                AppException.FirestoreServiceUnavailableException(
                    resourceManager.getString(R.string.error_firestore_unavailable)
                )
            else -> AppException.FirestoreUnknownException(
                resourceManager.getString(R.string.error_firestore_unknown)
            )
        }

    private fun handleGeneralFirebaseException(): AppException =
        AppException.FirebaseGenericException(resourceManager.getString(R.string.error_service_error))

    private fun isNetworkException(exception: Throwable): Boolean {
        val className = exception.javaClass.name
        return className.contains("ConnectException") ||
                className.contains("UnknownHostException") ||
                className.contains("ConnectTimeoutException") ||
                className.contains("SocketTimeoutException") ||
                exception.cause?.let { isNetworkException(it) } == true
    }

    private fun handleNetworkException(exception: Throwable): AppException {
        val className = exception.javaClass.name
        return when {
            className.contains("ConnectTimeoutException") || 
            className.contains("SocketTimeoutException") ->
                AppException.NetworkException(resourceManager.getString(R.string.error_connection_timeout))
            className.contains("ConnectException") ->
                AppException.NetworkException(resourceManager.getString(R.string.error_connection_failed))
            className.contains("UnknownHostException") ->
                AppException.NetworkException(resourceManager.getString(R.string.error_host_unreachable))
            else ->
                AppException.NetworkException(resourceManager.getString(R.string.error_network_error))
        }
    }

    private fun handleGeneralException(): AppException =
        AppException.GeneralException(resourceManager.getString(R.string.error_unexpected))

    private fun getHttpResponse(exception: Throwable): HttpResponse? =
        tryExtractResponse(exception) ?: exception.cause?.let { tryExtractResponse(it) }

    private fun tryExtractResponse(exception: Throwable): HttpResponse? =
        try {
            val className = exception.javaClass.name
            if (isHttpException(className)) {
                exception.javaClass.getDeclaredField("response")
                    .apply { isAccessible = true }
                    .get(exception) as? HttpResponse
            }
            else null
        } catch (e: Exception) {
            null
        }

    private fun isHttpException(className: String): Boolean =
        className.contains("HttpResponseException") ||
                className.contains("ClientRequestException") ||
                className.contains("ServerResponseException")

    private fun handleHttpException(response: HttpResponse, exception: Throwable): AppException =
        when (response.status.value) {
            400 -> AppException.HttpBadRequestException(resourceManager.getString(R.string.error_invalid_request))
            401 -> AppException.HttpUnauthorizedException(
                resourceManager.getString(R.string.error_authentication_required)
            )
            404 -> AppException.HttpNotFoundException(resourceManager.getString(R.string.error_resource_not_found))
            500 -> AppException.HttpInternalServerErrorException(resourceManager.getString(R.string.error_server_error))
            else -> AppException.HttpUnknownException(resourceManager.getString(R.string.error_network_error))
        }
}
