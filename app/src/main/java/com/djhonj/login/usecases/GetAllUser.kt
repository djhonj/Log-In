package com.djhonj.login.usecases

import com.djhonj.login.data.repository.UserRepository
import com.djhonj.login.domain.User

class GetAllUser(private val userRepository: UserRepository) {
    suspend fun invoke(): List<User> {
        return userRepository.getAllUser()
    }
}

