package hr.fer.filip.academyproject


import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.filip.academyproject.network.Network
import hr.fer.filip.model.Contributor
import hr.fer.filip.model.Issue
import hr.fer.filip.model.Pull
import hr.fer.filip.model.RepoDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class DetailsActivityViewModel : ViewModel() {

    private val _data = MutableLiveData<RepoDetails>()
    val data = _data.toImmutable()

    private val _contributorList = MutableLiveData<List<Contributor>>()
    val contributorList = _contributorList.toImmutable()

    private val _issueList = MutableLiveData<List<Issue>>()
    val issueList = _issueList.toImmutable()

    private val _pullList = MutableLiveData<List<Pull>>()
    val pullList = _pullList.toImmutable()


    fun headResponses(repoID: String) {

        viewModelScope.launch {
            val result = Network.service.getContributorsHead(repoID).headers()
            Log.d("xxxxxxxxxxxx", result.toString())
        }

    }

    fun getRepoDetails(repoID: String) {
        viewModelScope.launch {
            _data.value = Network.service.getRepoDetails(repoID)
        }

    }

    fun getContributorList(repoID : String) {
        viewModelScope.launch {
            _contributorList.value = Network.service.getContributors(repoID)
        }
    }

    fun getPullsList(repoID: String) {
        viewModelScope.launch {
            _pullList.value = Network.service.getPulls(repoID)
        }
    }

    fun getIssuesList(repoID: String) {
        viewModelScope.launch {
            _issueList.value = Network.service.getIssues(repoID)
        }
    }
}