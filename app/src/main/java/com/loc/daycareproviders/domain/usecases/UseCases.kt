package com.loc.daycareproviders.domain.usecases

data class UseCases(
    val createStudentAccount: CreateStudentAccount,
    val createTeacherAccount: CreateTeacherAccount,
    val createParentAccount: CreateParentAccount,
) {
}