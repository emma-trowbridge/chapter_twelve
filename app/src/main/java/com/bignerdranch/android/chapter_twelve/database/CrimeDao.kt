package com.bignerdranch.android.chapter_twelve.database

import androidx.room.Dao
import androidx.room.Query
import com.bignerdranch.android.chapter_twelve.Crime
import java.util.UUID


@Dao
interface CrimeDao {

    @Query("Select * From crime")
    suspend fun getCrimes(): List<Crime>

    @Query("Select * From crime Where id=(:id)")
    suspend fun getCrime(id:UUID):Crime

}

