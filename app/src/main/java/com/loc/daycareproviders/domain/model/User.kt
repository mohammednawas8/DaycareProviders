package com.loc.daycareproviders.domain.model

data class User(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val accountType: AccountType = AccountType.UNKNOWN,
){
}