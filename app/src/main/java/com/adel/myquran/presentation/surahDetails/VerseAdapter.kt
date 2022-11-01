package com.example.myqurancore.presentation.surahDetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myqurancore.databinding.VerseItemBinding
import com.example.myqurancore.presentation.surahDetails.uiState.VerseItemUiState

class VerseAdapter(
    private var verseList: List<VerseItemUiState>,
    private val onClickPlay: (String) -> Unit
) :
    RecyclerView.Adapter<VerseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: VerseItemBinding =
            VerseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            verseList[position].let { item ->
                binding.tvVerse.text = item.verseText
                binding.verseNum.text = item.verseNumber.toString()
                binding.ivPlayAudio.setOnClickListener { onClickPlay(item.verseAudio) }
            }
        }
    }

    override fun getItemCount(): Int = verseList.size

    inner class ViewHolder(val binding: VerseItemBinding) : RecyclerView.ViewHolder(binding.root)

}