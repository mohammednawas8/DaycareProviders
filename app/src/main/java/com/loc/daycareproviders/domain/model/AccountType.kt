package com.loc.daycareproviders.domain.model

enum class AccountType {
    DAYCARE_PROVIDER, NORMAL_USER, UNKNOWN
}

fun stringToAccountType(accountType: String?): AccountType {
    return when (accountType) {
        "DAYCARE_PROVIDER" -> AccountType.DAYCARE_PROVIDER
        "NORMAL_USER" -> AccountType.NORMAL_USER
        else -> AccountType.UNKNOWN
    }
}
