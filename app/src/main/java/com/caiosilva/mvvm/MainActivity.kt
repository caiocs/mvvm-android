package com.caiosilva.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.caiosilva.mvvm.adapters.MainAdapter
import com.caiosilva.mvvm.databinding.ActivityMainBinding
import com.caiosilva.mvvm.repositories.MainRepository
import com.caiosilva.mvvm.rest.RetrofitService
import com.caiosilva.mvvm.viewmodel.main.MainViewModel
import com.caiosilva.mvvm.viewmodel.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()

    private val adapter = MainAdapter { live ->

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MainViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)

        binding.recyclerView.adapter = adapter


    }

    override fun onStart() {
        super.onStart()

        viewModel.liveList.observe(this, Observer {lives ->
            Log.i("CaioSilva", "On Start")
            adapter.setLiveList(lives)
        })

        viewModel.errorMessage.observe(this, Observer {error ->
            Log.i("CaioSilva", "On Error")
            Toast.makeText(this, error, Toast.LENGTH_LONG).show()
        })

    }

    override fun onResume() {
        super.onResume()

        viewModel.getAllLives()
    }
}