package com.loc.daycareproviders.domain.model

data class User(
    val firstName: String,
    val lastName: String,
    val accountType: AccountType,
)