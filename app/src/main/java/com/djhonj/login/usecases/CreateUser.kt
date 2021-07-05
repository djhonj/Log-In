package com.djhonj.login.usecases

import com.djhonj.login.data.repository.UserRepository
import com.djhonj.login.domain.User

class CreateUser(private val userRepository: UserRepository) {
    suspend fun invoke(user: User) {
        userRepository.createUser(user)
    }
}