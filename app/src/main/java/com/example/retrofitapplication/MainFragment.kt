package com.example.retrofitapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitapplication.databinding.FragmentMainBinding
import com.example.retrofitapplication.model.MainViewModel
import com.example.retrofitapplication.model.Response
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private var binding: FragmentMainBinding? = null
    private var adapter: RecyclerAdapter? = null
    private val mainViewModel: MainViewModel by viewModels()

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

    private fun init() {
        initRecycler()
        loadNews()
    }

    private fun loadNews(){
        mainViewModel.getNews().observe(viewLifecycleOwner,{
            lifecycleScope.launch {
                adapter!!.submitData(it)
            }
        })
    }

    private fun initRecycler() {
        adapter = RecyclerAdapter()
        binding!!.rvNews.layoutManager = LinearLayoutManager(requireContext())
        binding!!.rvNews.adapter = adapter

    }

//    private fun observes() {
//        mainViewModel.newsLiveData.observe(viewLifecycleOwner, {
//            when (it) {
//                is Response.Success -> {
//                    adapter!!.setNews(it.data!!)
//                }
//                is Response.Error -> {
//                    Toast.makeText(requireActivity(), it.message, Toast.LENGTH_LONG)
//                }
//                is Response.Loading -> {
//                    if (it.loading)
//                        binding!!.progress.visibility = View.VISIBLE
//                    else
//                        binding!!.progress.visibility = View.GONE
//                }
//            }
//        })
//    }
}