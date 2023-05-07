package com.loc.daycareproviders.domain.model

enum class AccountType {
    STUDENT, TEACHER, PARENT, UNKNOWN
}

fun stringToAccountType(accountType: String): AccountType {
    return when(accountType){
        "STUDENT" -> AccountType.STUDENT
        "TEACHER" -> AccountType.TEACHER
        "PARENT" -> AccountType.PARENT
        else -> AccountType.UNKNOWN
    }
}
