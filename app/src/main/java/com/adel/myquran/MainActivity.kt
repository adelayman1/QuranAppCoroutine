package com.adel.myquran

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.adel.myquran.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding: ActivityMainBinding= ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
    }
}