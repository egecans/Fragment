package com.example.tokenfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.tokenfragment.data.db.entities.Tests
import com.example.tokenfragment.databinding.FragmentTest1Binding
import com.example.tokenfragment.databinding.FragmentTest2Binding
import com.example.tokenfragment.ui.TestsViewModel


class Test2 : Fragment() {

    private lateinit var mTestsViewModel: TestsViewModel
    private var _binding: FragmentTest2Binding? = null
    private val binding get() = _binding!!

    companion object{
        var failCount: Int = 0
        var successCount: Int = 0
        var lastResult: Boolean = false
        var info: String = ""
        var test = Tests(2,"Test1", successCount, failCount, false, info)    //0 0 olunca eklemiyomuş
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTest2Binding.inflate(inflater,container,false)   //bu null olabilir
        return binding.root     //ama bu olamaz
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTestsViewModel = ViewModelProvider(this).get(TestsViewModel::class.java)

        binding.btnSubmitTest2.setOnClickListener {


            var result =""
            if (binding.rbSuccessTest2.isChecked ){
                successCount++
                lastResult = true
                info = binding.etInfo.text.toString()
                result = "Success"
            }
            else{
                failCount++
                lastResult = false
                info = binding.etInfo.text.toString()
                result = "Fail"
            }
            Log.d("testID before","${test.id}")
            if (successCount + failCount <= 1){
                test = Tests(2,"Test1", successCount, failCount, lastResult, info) //companiondakini değiştirsin tıklayınca
                insertDatatoDatabase(test)
                Log.d("Test2", "Insert successfully")
            }
            else{
                test = Tests(2,"Test1", successCount, failCount, lastResult, info)
                mTestsViewModel.update(test)
                Log.d("Test2", "Update successfully")
            }
            Log.d("testID after","${test.id}")
            Log.d("test","$test.toString()}")


            Toast.makeText(requireContext(),"Successfully added!", Toast.LENGTH_SHORT).show()
            val tag = "Test2"   //fragment adı getirebilirsem çok iyi ama bulamadım
            val str = "You are $result on $tag with info: $info"
            Log.d(tag, str)
        }
    }

    private fun insertDatatoDatabase(test: Tests) { //suspend yaptım
        mTestsViewModel.insert(test)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null //bidaha null yapsın destroyladığında memory leak olmasın
    }



}