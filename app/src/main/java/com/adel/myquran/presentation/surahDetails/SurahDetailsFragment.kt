package com.adel.myquran.presentation.surahDetails

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
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
import com.adel.myquran.data.models.VerseModel
import com.adel.myquran.databinding.FragmentSurahDetailsBinding
import com.adel.myquran.domain.entities.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SurahDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSurahDetailsBinding
    private lateinit var viewModel: SurahDetailsViewModel
    private lateinit var navController: NavController
    private var allVersesList: MutableList<VerseModel> = mutableListOf()
    private lateinit var adapter: VerseAdapter
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SurahDetailsViewModel::class.java)
        adapter = VerseAdapter(allVersesList) { audioLink ->
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                            AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                    )
                    setDataSource(audioLink)
                    prepare()
                    start()
                    setOnCompletionListener {
                        mediaPlayer!!.release()
                        mediaPlayer = null
                    }
                }
            }
        }
        binding.rcVerses.layoutManager = LinearLayoutManager(requireContext())
        binding.rcVerses.adapter = adapter

        lifecycleScope.launch(Dispatchers.IO) {
            whenStarted {
                launch {
                    viewModel.surahDetails.collect {
                        when (it) {
                            is Result.Loading -> {
                            }
                            is Result.Success -> {
                                with(it.data) {
                                    binding.tvSurahName.text = name
                                    binding.tvSurahTitleName.text = name
                                    binding.tvSurahArabicName.text = arabicName
                                    binding.tvSurahType.text = "$type - "
                                    binding.tvSurahVerseNum.text = "$verseNum VERSES"
                                }
                            }
                            is Result.Error -> null
                        }

                    }
                }
                launch {
                    viewModel.verseList.collect {
                        when (it) {
                            is Result.Loading -> {
                                binding.scrollViewSurahDetails.visibility = View.GONE
                                binding.progressLoading.visibility = View.VISIBLE
                            }
                            is Result.Success -> {
                                binding.scrollViewSurahDetails.visibility = View.VISIBLE
                                binding.progressLoading.visibility = View.GONE
                                allVersesList.clear()
                                allVersesList.addAll(it.data ?: mutableListOf())
                                adapter.notifyDataSetChanged()
                            }
                            is Result.Error -> Toast.makeText(requireContext(),
                                    "${it.error.javaClass.simpleName} + error",
                                    Toast.LENGTH_SHORT).show()
                        }

                    }
                }

            }
        }
        binding.ivBack.setOnClickListener {
            navController.navigateUp()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSurahDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


}
