package com.adel.myquran.presentation.homeScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adel.myquran.data.models.SurahModel
import com.adel.myquran.databinding.FragmentHomeBinding
import com.adel.myquran.domain.entities.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding
    private var allSurahList: MutableList<SurahModel> = mutableListOf()
    private lateinit var adapter: SurahAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.rcAllSurah.layoutManager = LinearLayoutManager(requireContext())
        adapter = SurahAdapter(allSurahList, { surahNum ->
            val actionToSurahDetailsFragment =
                    HomeFragmentDirections.actionHomeFragmentToSurahDetailsFragment(surahNum)
            navController.navigate(actionToSurahDetailsFragment)
        })
        binding.rcAllSurah.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {
            whenStarted {
                viewModel.surahList.collect {
                    when (it) {
                        is Result.Loading -> {
                            binding.progressLoading.visibility = View.VISIBLE
                            binding.scrollViewSurah.visibility = View.GONE
                            binding.lnError.visibility = View.GONE
                        }
                        is Result.Success -> {
                            binding.progressLoading.visibility = View.GONE
                            binding.scrollViewSurah.visibility = View.VISIBLE
                            binding.lnError.visibility = View.GONE
                            allSurahList.clear()
                            allSurahList.addAll(it.data)
                            adapter.notifyDataSetChanged()
                        }
                        is Result.Error -> {
                            Toast.makeText(requireContext(),
                                    "${it.error.javaClass.simpleName} + error",
                                    Toast.LENGTH_SHORT).show()

                            binding.progressLoading.visibility = View.GONE
                            binding.scrollViewSurah.visibility = View.GONE
                            binding.lnError.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
        binding.btnTryAgain.setOnClickListener {
            viewModel.loadData()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

}