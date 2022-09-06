package com.example.tokenfragment.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tokenfragment.data.db.TestsDao
import com.example.tokenfragment.data.db.TestsDatabase
import com.example.tokenfragment.data.db.entities.Tests
import com.example.tokenfragment.data.repositories.TestRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


//Activity değil application context tutabilirsin
//provide data to UI and survive configuration changes. Like communication center btw Repo and UI
class TestsViewModel(application: Application): AndroidViewModel(application){

    val getAllTests: LiveData<List<Tests>>
    private val repository: TestRepository

    init {
        Log.d("ViewModel","Init geldi") //buraya girmiyo, girmediğine göre getAllTestsi de yapmıyor
        val testDB = TestsDatabase.invoke(application)
        val testDao = testDB.getTestsDao()
        repository = TestRepository(testDB)
        getAllTests = repository.getAllTests()
    }

    /*
     fun upsert(tests: Tests){  //suspende gerek kalmadı Dispatcher içinde kullandığı için
        viewModelScope.launch(Dispatchers.IO) { //mainde değil IO background threadde çalıştır
            repository.upsert(tests)
        }
    }
     */

    fun insert(tests: Tests){
        viewModelScope.launch(Dispatchers.IO) {
            //Log.d("ViewModel","Insert Geldi")
            repository.insert(tests)
        }
    }

    fun update(tests: Tests){
        viewModelScope.launch(Dispatchers.IO) {
            //Log.d("ViewModel","Update Geldi")
            repository.update(tests)
        }
    }

}


    /*
    (
    private val repository: TestRepository
): ViewModel() {

    //Coroutine olduğundan suspende gerek kalmadı artık
    //Main threadde yapıyor işlemi
    fun upsert(test: Tests) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(test)     //suspend fun o yüzden ok işareti var,
    }

    fun getAllTests() = repository.getAllTests()

    //viewModelde parametre var ama
    //ViewModelProviders.of(this).get(ShoppingViewModel::class.java) da parametre giremiyon bu da error verdiriyor
    //bu yüzden ViewModelFactory'e ihtiyacın var
}
*/
