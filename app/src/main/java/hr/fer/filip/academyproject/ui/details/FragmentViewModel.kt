package hr.fer.filip.academyproject.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hr.fer.filip.academyproject.network.Network
import hr.fer.filip.academyproject.toImmutable
import hr.fer.filip.model.Contributor
import hr.fer.filip.model.Issue
import hr.fer.filip.model.Pull
import kotlinx.coroutines.launch

class FragmentViewModel : ViewModel() {

    private val _contributorList = MutableLiveData<List<Contributor>>()
    val contributorList = _contributorList.toImmutable()

    private val _issueList = MutableLiveData<List<Issue>>()
    val issueList = _issueList.toImmutable()

    private val _pullList = MutableLiveData<List<Pull>>()
    val pullList = _pullList.toImmutable()

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