package hr.fer.filip.academyproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.filip.academyproject.network.Network
import hr.fer.filip.model.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _data = MutableLiveData<List<Repository>>()
    val data = _data.toImmutable()

    fun loadRepoDetailsList() {

        viewModelScope.launch {
            _data.value = Network.service.getRepos("square")
        }

    }




}