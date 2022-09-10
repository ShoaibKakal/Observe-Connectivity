package com.example.observeinternetconnection

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var connectionViewModel: ConnectionViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factoryConnectionViewModel =
            ConnectionViewModel.FactoryConnectionVM(ConnectionRepo(applicationContext))
        connectionViewModel =
            ViewModelProvider(this, factoryConnectionViewModel)[ConnectionViewModel::class.java]
        connectionViewModel.checkConnection()



        initObservers()
    }

    private fun initObservers() {

        connectionViewModel.connectionStatus.observe(this) {
            findViewById<TextView>(R.id.tv_network_status).text = it.toString()
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
            Log.d("TestTag", "initObservers: Connection: $it")
        }
    }
}