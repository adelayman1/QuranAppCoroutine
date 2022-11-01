package com.adel.myquran.presentation.surahDetails

import android.media.AudioAttributes
import android.media.MediaPlayer
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
import com.adel.myquran.databinding.FragmentSurahDetailsBinding
import com.adel.myquran.presentation.surahDetails.uiState.VerseItemUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SurahDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSurahDetailsBinding
    private lateinit var viewModel: SurahDetailsViewModel
    private lateinit var navController: NavController
    private var allVersesList: MutableList<VerseItemUiState> = mutableListOf()
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

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.surahUiState.collect { uiState ->
                    if (uiState.isLoading) {
                        binding.scrollViewSurahDetails.visibility = View.GONE
                        binding.progressLoading.visibility = View.VISIBLE
                    } else {
                        binding.progressLoading.visibility = View.GONE
                        binding.scrollViewSurahDetails.visibility = View.VISIBLE
                        binding.progressLoading.visibility = View.GONE
                    }
                    uiState.verses?.let {
                        allVersesList.clear()
                        allVersesList.addAll(it)
                        adapter.notifyDataSetChanged()
                    }
                    uiState.surahInfo?.let {
                        binding.tvSurahName.text = it.englishName
                        binding.tvSurahTitleName.text = it.englishName
                        binding.tvSurahArabicName.text = it.arabicName
                        binding.tvSurahType.text = "${if (it.isMeccen) "Meccen" else "Medinan"} - "
                        binding.tvSurahVerseNum.text = "${it.versesNumber} VERSES"
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
