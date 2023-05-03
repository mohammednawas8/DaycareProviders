package com.loc.daycareproviders.domain.repository

import com.loc.daycareproviders.domain.model.Parent
import com.loc.daycareproviders.domain.model.Student
import com.loc.daycareproviders.domain.model.Teacher
import com.loc.daycareproviders.util.DataState

interface AuthenticationRepository {

    suspend fun createStudentAccount(student: Student, email: String,password: String): Student?

    suspend fun createParentAccount(parent: Parent, email: String,password: String): Parent?

    suspend fun createTeacherAccount(teacher: Teacher, email: String,password: String): Teacher?

}

