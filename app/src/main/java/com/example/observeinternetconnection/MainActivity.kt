package com.example.observeinternetconnection

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var connectionViewModel: ConnectionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factoryConnectionViewModel = ConnectionViewModel.FactoryConnectionVM(ConnectionRepo(applicationContext))
        connectionViewModel = ViewModelProvider(this, factoryConnectionViewModel)[ConnectionViewModel::class.java]
        connectionViewModel.checkConnection()



        initObservers()
    }

    private fun initObservers() {

        connectionViewModel.connectionStatus.observe(this){
            Log.d("TestTag", "initObservers: Connection: $it")
        }
    }
}