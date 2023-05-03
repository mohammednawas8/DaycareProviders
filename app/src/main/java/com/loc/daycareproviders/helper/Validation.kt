package com.loc.daycareproviders.helper

import android.util.Patterns


fun validateEmail(email: String): ValidationResult {
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return ValidationResult(
            isValid = false,
            message = "Wrong email format"
        )
    }
    return ValidationResult(
        isValid = true,
        message = null
    )
}

fun validatePassword(password: String): ValidationResult {
    if (password.length < 6) {
        return ValidationResult(
            isValid = false,
            message = "Password must be more than 6 characters"
        )
    }
    return ValidationResult(
        isValid = true,
        message = null
    )
}

data class ValidationResult(
    val isValid: Boolean,
    val message: String?,
)
