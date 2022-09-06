package com.example.tokenfragment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tokenfragment.data.repositories.TestRepository
/*
//ViewModelProviders.of(this).get(ShoppingViewModel::class.java) da parametre giremiyon bu da error verdiriyor
//bu yüzden ViewModelFactory'e ihtiyacın var
class TestsViewModelFactory(
    private val repository: TestRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TestsViewModel::class.java)) {
            return TestsViewModel(repository) as T
        }
        throw IllegalArgumentException("ViewModel class not found") //Assignable değilse error vermesin diye
    }
}
        //return TestsViewModel(repository) as T   //Error vermesin bu generic sanırım
*/