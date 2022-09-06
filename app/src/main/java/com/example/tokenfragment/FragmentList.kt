package com.example.tokenfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tokenfragment.databinding.FragmentListBinding
import com.example.tokenfragment.other.TestAdapter
import com.example.tokenfragment.ui.TestsViewModel


class FragmentList : Fragment() {
    //the lateinit keyword is used for those variables which are initialized after the declaration
    private lateinit var mTestViewModel: TestsViewModel
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!  //binding null olmayan _bindinge eşit ? ama get ne alaka

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //val view = inflater.inflate(R.layout.fragment_list, container, false)
        _binding = FragmentListBinding.inflate(inflater,container,false)
        val view = binding.root

        //burada yapıyorum ama hata alırsam onViewCreatedda yap
        //Recycler view
        val adapter = TestAdapter()
        val recyclerView = binding.recyclerview  //fragment_listten aldım
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //ViewModel
        mTestViewModel = ViewModelProvider(this).get(TestsViewModel::class.java)
        //this olmadı bu oluyor lifecycleda
        //emulatoru aşağı yukarı aldım falan bir de test1 e bastım data girerken öyle yazdırdı
        //rebuild yapabilirsin belki
        //içine giriyor, getAllTestse gitmiyor?
        mTestViewModel.getAllTests.observe(viewLifecycleOwner, Observer { testList ->
            adapter.setData(testList)   //notify datachanged yapıyon bu methodda zaten
            Log.d("SetData","Data Notified")
        })



        return view
    }

    //to avoid memory leak
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}