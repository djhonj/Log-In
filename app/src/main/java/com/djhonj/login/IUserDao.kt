package com.djhonj.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete

@Dao
interface IUserDao {
    @Query("select * from User")
    suspend fun getUserAll(): List<User>

    @Query("select * from User where Id = :id")
    suspend fun getUser(id: String): User

    @Insert
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)
}