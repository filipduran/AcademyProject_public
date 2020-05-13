package hr.fer.filip.academyproject.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.fer.filip.academyproject.databinding.ItemContributorBinding
import hr.fer.filip.academyproject.databinding.ItemIssueBinding
import hr.fer.filip.model.Contributor
import hr.fer.filip.model.Issue
import kotlin.collections.ArrayList

class FragmentRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val recyclerList = ArrayList<Any>()
    private val TYPE_CONTRIBUTOR = 0
    private val TYPE_ISSUE = 1
    private val TYPE_ERROR = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when(viewType) {
            TYPE_CONTRIBUTOR -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemContributorBinding.inflate(inflater, parent, false)
                return ContributorViewHolder(binding)
            }
            TYPE_ISSUE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemIssueBinding.inflate(inflater, parent, false)
                return IssueViewHolder(binding)
            }
            else -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemContributorBinding.inflate(inflater, parent, false)
                return ContributorViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(recyclerList[position] is Contributor) {
            (holder as ContributorViewHolder).binding.contributorsName.setText((recyclerList[position] as Contributor).login)
        }
        if(recyclerList[position] is Issue) {
            (holder as IssueViewHolder).binding.issueTitle.setText((recyclerList[position] as Issue).title)
        }
    }

    fun updateList(newList : List<Any>) {
        val result = DiffUtil.calculateDiff(FragmentListCallBack(this.recyclerList,newList))
        this.recyclerList.clear()
        this.recyclerList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        when(recyclerList[position]) {
            is Contributor -> return TYPE_CONTRIBUTOR
            is Issue -> return TYPE_ISSUE
            else -> return TYPE_ERROR
        }
    }
}