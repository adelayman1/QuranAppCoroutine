package com.example.myqurancore.presentation.homeScreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myqurancore.databinding.SurahItemBinding
import com.example.myqurancore.presentation.homeScreen.uiStates.SurahItemUiState

class SurahAdapter(
    private var surahList: List<SurahItemUiState>,
    private var onSurahClick: (Int) -> Unit
) : RecyclerView.Adapter<SurahAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: SurahItemBinding =
            SurahItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            surahList[position].let { item ->
                binding.tvSurahArabicName.text = item.surahArabicName
                binding.tvSurahName.text = item.surahEnglishName
                binding.tvSurahType.text = item.surahType + " - "
                binding.tvSurahVerseNum.text = item.versesNumInSurah.toString()
                itemView.setOnClickListener { onSurahClick(position + 1) }
            }
            binding.tvSurahNum.text = (position + 1).toString()
        }
    }

    override fun getItemCount(): Int = surahList.size

    inner class ViewHolder(val binding: SurahItemBinding) : RecyclerView.ViewHolder(binding.root)

}