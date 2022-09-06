package com.example.tokenfragment.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tokenfragment.data.db.entities.Tests

//burada methodların gerekliliklerini tanımlayacaksın, sonra üzerine yazacaksın
//Defining methods that access the database
@Dao
interface TestsDao {

    /*
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(tests: Tests)
     */

    @Insert(onConflict = OnConflictStrategy.IGNORE) //if all same, ignore it
    suspend fun insert(tests:Tests)

    @Update
    suspend fun update(tests: Tests)

    @Query("SELECT * FROM tests")
    fun getAllTests(): LiveData<List<Tests>>
    //degisecek burası o yüzden live data




}