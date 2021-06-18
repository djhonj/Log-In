package com.djhonj.login

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy

@Dao
interface IUserDao {
    @Query("select * from User")
    suspend fun getUserAll(): List<User>

    @Query("select * from User where UserName = :userName")
    suspend fun getUser(userName: String): User

    @Query("select * from User where UserName = :userName and Password = :password")
    suspend fun getUser(userName: String, password: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    //@Delete
    //fun deleteUser(user: User)
}