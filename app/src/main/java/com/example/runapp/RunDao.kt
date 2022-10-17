package com.example.runapp

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RunDao{
    @Query("SELECT * FROM run_table")
    fun getAll(): Flow<List<RunEntity>>

    @Insert
    fun insert(run_activity: RunEntity)
}