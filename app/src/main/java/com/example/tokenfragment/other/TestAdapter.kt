package com.example.tokenfragment.other

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tokenfragment.R
import com.example.tokenfragment.data.db.entities.Tests
import com.example.tokenfragment.databinding.TestBinding
import com.example.tokenfragment.ui.TestsViewModel
import kotlin.math.log

class TestAdapter(
    //var tests: List<Tests>,   //parametresiz de oluyo demek ki
    //private val viewModel: TestsViewModel
): RecyclerView.Adapter<TestAdapter.TestViewHolder>() {

    //gerekliydi inherit için
    //inner class TestViewHolder(testView: View): RecyclerView.ViewHolder(testView)
    inner class TestViewHolder(val binding: TestBinding): RecyclerView.ViewHolder(binding.root)

    private var testList = emptyList<Tests>()

    //View Holder'ı createledin
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.test,parent,false)
        //return TestViewHolder(view)
        val binding = TestBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val currTest = testList[position]
            //holder.itemView.findViewById<TextView>(R.id.tvTag).text = currTest.tag
        holder.binding.tvTag.text = currTest.tag
        holder.binding.tvInfo.text = currTest.info
        holder.binding.tvCountFail.text = "${currTest.failCount}"
        holder.binding.tvCountSuccess.text = currTest.successCount.toString()
        holder.binding.tvLastResult.text = if (currTest.successLast) "Success" else "Fail"

        //butonlara setClickOn yaptı ama ben yapamam bunu parametre atayabilirim belki?

    }

    override fun getItemCount(): Int {
        return testList.size
    }

    fun setData(test: List<Tests>){
        Log.d("Adapter/setDATA","${test.toString()}")
        this.testList = test
        notifyDataSetChanged()
    }

}