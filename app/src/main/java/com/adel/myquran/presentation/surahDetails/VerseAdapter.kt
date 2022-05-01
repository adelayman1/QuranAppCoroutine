package com.adel.myquran.presentation.surahDetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adel.myquran.data.model.VerseModel
import com.adel.myquran.databinding.VerseItemBinding

class VerseAdapter(private var verseList: List<VerseModel>, private val listener:IVerseItemClick) :
    RecyclerView.Adapter<VerseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerseAdapter.ViewHolder {
        val binding: VerseItemBinding =
            VerseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VerseAdapter.ViewHolder, position: Int) {
        with(holder) {
            verseList[position].let { item ->
                binding.tvVerse.text = item.text
                binding.verseNum.text = item.verseNumber.toString()
                binding.ivPlayAudio.setOnClickListener{ listener.OnPlayClickListener(item.audio)}
            }
        }
    }

    override fun getItemCount(): Int = verseList.size

    inner class ViewHolder(val binding: VerseItemBinding) : RecyclerView.ViewHolder(binding.root)

}