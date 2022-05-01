package com.adel.myquran.presentation.homeScreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adel.myquran.data.model.SurahModel
import com.adel.myquran.databinding.SurahItemBinding

class SurahAdapter (private var surahList: List<SurahModel>, private var listener: ISurahItemClick) : RecyclerView.Adapter<SurahAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SurahAdapter.ViewHolder {
        val binding:SurahItemBinding= SurahItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: SurahAdapter.ViewHolder, position: Int) {
        with(holder){
            surahList[position].let { item->
                binding.tvSurahArabicName.text=item.arabicName
                binding.tvSurahName.text=item.name
                binding.tvSurahType.text=item.type+" - "
                binding.tvSurahVerseNum.text= item.verseNum.toString()
                itemView.setOnClickListener{ listener.onItemClickListener(position+1) }
            }
            binding.tvSurahNum.text= (position+1).toString()
        }
    }

    override fun getItemCount(): Int =surahList.size

    inner class ViewHolder(val binding: SurahItemBinding):RecyclerView.ViewHolder(binding.root)

}