package com.example.observeinternetconnection

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch

class ConnectionViewModel(private val connectionRepo: ConnectionRepo) : ViewModel() {

    private var _connectionStatus = MutableLiveData<Connection>()
    val connectionStatus: LiveData<Connection> = _connectionStatus


    fun checkConnection() {
        viewModelScope.launch {
            connectionRepo.observeConnection()
                .collect {
                    _connectionStatus.value = it
                }
        }
    }


    class FactoryConnectionVM(private val connectionRepo: ConnectionRepo) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ConnectionViewModel::class.java)) {
                return ConnectionViewModel(connectionRepo) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}