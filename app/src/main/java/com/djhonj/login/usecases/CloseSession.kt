package com.djhonj.login.usecases

import com.djhonj.login.domain.User
import com.djhonj.login.data.repository.UserRepository

class CloseSession(private val userRepository: UserRepository) {
    suspend fun invoke(user: User) {
        userRepository.updateUser(user)
    }
}