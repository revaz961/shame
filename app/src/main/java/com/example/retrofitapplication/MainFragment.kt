package com.example.retrofitapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitapplication.databinding.FragmentMainBinding
import com.example.retrofitapplication.model.MainViewModel

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private var adapter: RecyclerAdapter? = null
    private val mainViewModel:MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (binding == null) {
            binding = FragmentMainBinding.inflate(inflater, container, false)
            init()
        }
        return binding!!.root
    }

    private fun init(){
        initRecycler()
        observes()
        binding!!.btnLoad.setOnClickListener {
            binding!!.btnLoad.visibility = View.GONE
            binding!!.progress.visibility = View.VISIBLE
            mainViewModel.init()
        }
    }

    private fun initRecycler(){
        adapter = RecyclerAdapter()
        binding!!.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvNews.adapter = adapter

    }

    private fun observes(){
        mainViewModel._newsLiveData.observe(viewLifecycleOwner,{
            adapter!!.setNews(it)
            binding!!.progress.visibility = View.GONE
        })
    }
}