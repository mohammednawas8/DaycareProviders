package com.loc.daycareproviders.domain.model

data class User(
    val id: String,
    val firstName: String,
    val lastName: String,
    val accountType: AccountType,
)