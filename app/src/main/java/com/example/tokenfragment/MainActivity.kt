package com.example.tokenfragment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tokenfragment.data.db.TestsDatabase
import com.example.tokenfragment.data.db.entities.Tests
import com.example.tokenfragment.data.repositories.TestRepository
import com.example.tokenfragment.databinding.ActivityMainBinding
import com.example.tokenfragment.other.TestAdapter
import com.example.tokenfragment.ui.TestsViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein //kendimiz importladık
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity() { //KodeinAware

    /*
    override val kodein by kodein()
    private val factory: TestsViewModelFactory by instance()    //from provider
*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)




        /*
        /*
        val database: TestsDatabase  = TestsDatabase(this)
        val repository = TestRepository(database)
        val factory = TestsViewModelFactory(repository) */
        //viewModel provider is used to connect UI Controller with ViewModel
        val viewModel = ViewModelProvider(this,factory).get(TestsViewModel::class.java)
        //factorysiz çalışmadı TestsViewModelde parametre girmek gerektiğinden

        val adapter = TestAdapter(listOf(),viewModel)
        rvTests.layoutManager = LinearLayoutManager(this)
        rvTests.adapter = adapter

        viewModel.getAllTests().observe(this, Observer {
            adapter.tests = it  //testler bu Daodaki döndüğü lifecycledaki list of tests
            adapter.notifyDataSetChanged()
        })
        */

        val emptyFragment = EmptyFragment()     //call this for initial visualization
        val test1Fragment = Test1()
        val test2Fragment = Test2()
        val fragmentList = FragmentList()

        //ctrl + q ile bak serializable dönüyo

        //rv için olanı ayarladın
        supportFragmentManager.beginTransaction().apply {
            Log.d("Main","yeniliyor")
            replace(R.id.rvTests,fragmentList)
            commit()
        }

        //define our initial visualization here
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,emptyFragment)
            commit()
        }

        //findViewById<Button>(R.id.btnTest1)
        binding.btnTest1.setOnClickListener {
            /* startActivity(Intent(this,Test1Activity::class.java)) */
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,test1Fragment)
                addToBackStack(null)    //now it adds it to the fragment stack, otherwise just start a new fragment
                commit()
            }
            /*val test1 = intent.getSerializableExtra("EXTRA_TEST1") as Tests
            viewModel.upsert(test1) */
        }

        binding.btnTest2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,test2Fragment)
                addToBackStack(null)
                commit()
            }
        }

        //create a dialog
        val test3Dialog = AlertDialog.Builder(this)
            .setTitle("Test 3")
            .setIcon(R.drawable.ic_test)
            .setMessage("Did you pass the test3?")
            .setNegativeButton("NO"){ _, _ ->   //dialogInterface, i  aren't needed
                Log.d("Test3","You are failed on Test3")
                Toast.makeText(this,"You are failed on Test3",Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("YES"){ _, _ ->
                Log.d("Test3","You are successed on Test3")
                Toast.makeText(this,"You are successed on Test3",Toast.LENGTH_SHORT).show()
            }

        binding.btnTest3.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.flFragment,emptyFragment)
                addToBackStack(null)
                commit()
            }
            test3Dialog.show()
        }



    }
}