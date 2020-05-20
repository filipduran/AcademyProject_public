package hr.fer.filip.academyproject


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
import kotlinx.coroutines.launch

class DetailsActivityViewModel : ViewModel() {

    private val _repoDetails = MutableLiveData<RepoDetails>()
    val repoDetails = _repoDetails.toImmutable()

    private val _contributorList = MutableLiveData<List<Contributor>>()
    val contributorList = _contributorList.toImmutable()

    private val _issueList = MutableLiveData<List<Issue>>()
    val issueList = _issueList.toImmutable()

    private val _pullList = MutableLiveData<List<Pull>>()
    val pullList = _pullList.toImmutable()

    private val _headerResponses = MutableLiveData<List<Int>>()
    val headerResponses = _headerResponses.toImmutable()


    fun headResponses(orgName: String, repoID: String) {

        viewModelScope.launch {
            val result = ArrayList<Int>()
            val call1 = async {
                when (val response = safeResponse { Network.service.getContributorsHead(orgName,repoID) })  {
                    is Result.Success -> {
                        response.data.code()
                    }
                    is Result.Error -> {
                        Log.d("xxxxxxxxxxxxxxxx", "Error with call1: " + response.error.message)
                        return@async -1
                    }
                }

            }
            val call2 = async {
                when (val response = safeResponse { Network.service.getIssuesHead(orgName,repoID) })  {
                    is Result.Success -> {
                        response.data.code()
                    }
                    is Result.Error -> {
                        Log.d("xxxxxxxxxxxxxxxx", "Error with call1: " + response.error.message)
                        return@async -1
                    }
                }
            }
            val call3 = async {
                when (val response = safeResponse { Network.service.getPullsHead(orgName,repoID) })  {
                    is Result.Success -> {
                        response.data.code()
                    }
                    is Result.Error -> {
                        Log.d("xxxxxxxxxxxxxxxx", "Error with call1: " + response.error.message)
                        return@async -1
                    }
                }
            }
            result.add(call1.await())
            result.add(call2.await())
            result.add(call3.await())

            _headerResponses.value = result
        }

    }

    fun getRepoDetails(orgName:String, repoID: String) {
        viewModelScope.launch {
            when(val result = safeResponse { Network.service.getRepoDetails(orgName, repoID) }) {
                is Result.Success -> {
                    _repoDetails.value = result.data
                }
                is Result.Error -> {
                    Log.d("xxxxxxxxxxxxxxxx", "Error with repoDetails call" + result.error)
                }
            }

        }

    }

    fun getContributorList(orgName: String, repoID : String) {
        viewModelScope.launch {
            when(val result = safeResponse { Network.service.getContributors(orgName, repoID) }) {
                is Result.Success -> {
                    _contributorList.value = result.data
                }
                is Result.Error -> {
                    Log.d("xxxxxxxxxxxxxxxx", "Error with contributorsList call" + result.error)
                }
            }
        }
    }

    fun getPullsList(orgName: String, repoID: String) {
        viewModelScope.launch {
            when(val result = safeResponse { Network.service.getPulls(orgName, repoID) }) {
                is Result.Success -> {
                    _pullList.value = result.data
                }
                is Result.Error -> {
                    Log.d("xxxxxxxxxxxxxxxx", "Error with pullList call" + result.error)
                }
            }
        }
    }

    fun getIssuesList(orgName: String, repoID: String) {
        viewModelScope.launch {
            when(val result = safeResponse { Network.service.getIssues(orgName, repoID) }) {
                is Result.Success -> {
                    _issueList.value = result.data
                }
                is Result.Error -> {
                    Log.d("xxxxxxxxxxxxxxxx", "Error with issueList call" + result.error)
                }
            }
        }
    }
}