package com.bignerdranch.android.chapter_twelve

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bignerdranch.android.chapter_twelve.database.CrimeDatabase
import java.util.UUID

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context){

    private val database:CrimeDatabase= Room.databaseBuilder(
        context.applicationContext,
        CrimeDatabase::class.java,
        DATABASE_NAME
    )
        .createFromAsset(DATABASE_NAME)
        .build()

    suspend fun getCrimes():List<Crime> = database.crimeDAO().getCrimes()
    suspend fun getCrime(id:UUID):Crime = database.crimeDAO().getCrime(id)

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE
                ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}