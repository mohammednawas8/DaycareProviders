package com.loc.daycareproviders.helper

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException

fun getErrorMessage(e: Exception): String {
    return "Not Implemented yet"
}


fun getLoginErrorMessage(e: Exception): String {
    return when (e) {
        is FirebaseAuthInvalidUserException -> "User not found"
        is FirebaseAuthInvalidCredentialsException -> "User not found"
        else -> "Unknown Error"
    }
}

fun getRegisterErrorMessage(e: Exception): String {
    return when (e) {
        is FirebaseAuthUserCollisionException -> "The email address is already in use by another account"
        else -> "Unknown Error"
    }
}


