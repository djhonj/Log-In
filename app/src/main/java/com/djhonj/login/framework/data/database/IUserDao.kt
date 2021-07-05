package com.djhonj.login.framework.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.OnConflictStrategy
import com.djhonj.login.framework.data.database.User

@Dao
interface IUserDao {
    @Query("select * from User")
    fun getUserAll(): List<User>

    @Query("select * from User where UserName = :userName")
    fun getUser(userName: String): User

    @Query("select * from User where UserName = :userName and Password = :password")
    fun getUser(userName: String, password: String): User

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    //@Delete
    //fun deleteUser(user: User)
}