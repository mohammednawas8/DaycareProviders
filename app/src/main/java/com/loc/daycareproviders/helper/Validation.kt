package com.loc.daycareproviders.helper

import android.util.Log
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

fun validateFirstName(name: String): ValidationResult {
   return if (name.isEmpty()){
        ValidationResult(
            isValid = false,
            message = "First name cannot be empty"
        )
    }else{
       ValidationResult(
           isValid = true,
           message = null
       )
   }
}

fun validateLastName(name: String): ValidationResult {
    return if (name.isEmpty()){
        ValidationResult(
            isValid = false,
            message = "Last name cannot be empty"
        )
    }else{
        ValidationResult(
            isValid = true,
            message = null
        )
    }
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
