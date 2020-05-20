package hr.fer.filip.academyproject

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.filip.academyproject.network.Network
import hr.fer.filip.model.RepoDetails
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val _repoList = MutableLiveData<List<RepoDetails>>()
    val repoList = _repoList.toImmutable()

    fun loadRepoDetailsList(orgName : String) {

        viewModelScope.launch {
            when (val result = safeResponse { Network.service.getRepos(orgName) }) {
                is Result.Success -> {
                    _repoList.value = result.data
                }
                is Result.Error -> {
                    Log.d("MainActivityViewModel", "Error with network call")
                }
            }
        }

    }

}