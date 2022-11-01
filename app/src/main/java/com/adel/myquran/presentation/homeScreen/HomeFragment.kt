package com.adel.myquran.presentation.homeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adel.myquran.databinding.FragmentHomeBinding
import com.adel.myquran.presentation.homeScreen.uiStates.SurahItemUiState
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding
    private var allSurahList: MutableList<SurahItemUiState> = mutableListOf()
    private lateinit var adapter: SurahAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        binding.rcAllSurah.layoutManager = LinearLayoutManager(requireContext())
        adapter = SurahAdapter(allSurahList) { surahNum ->
            val actionToSurahDetailsFragment =
                HomeFragmentDirections.actionHomeFragmentToSurahDetailsFragment(surahNum)
            navController.navigate(actionToSurahDetailsFragment)
        }
        binding.rcAllSurah.adapter = adapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.surahUiState.collect { uiState ->
                    if (uiState.isLoading) {
                        binding.progressLoading.visibility = View.VISIBLE
                        binding.scrollViewSurah.visibility = View.GONE
                        binding.lnError.visibility = View.GONE
                    } else {
                        binding.progressLoading.visibility = View.GONE
                        binding.scrollViewSurah.visibility = View.VISIBLE
                        binding.lnError.visibility = View.GONE
                    }
                    uiState.surah?.let {
                        allSurahList.clear()
                        allSurahList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                    uiState.errorMessage?.let {
                        Toast.makeText(
                            requireContext(),
                            it,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel.errorMessageShown()
                    }
                }
            }
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