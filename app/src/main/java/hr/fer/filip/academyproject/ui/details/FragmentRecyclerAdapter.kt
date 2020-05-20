package hr.fer.filip.academyproject.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.fer.filip.academyproject.databinding.ItemContributorBinding
import hr.fer.filip.academyproject.databinding.ItemIssueBinding
import hr.fer.filip.academyproject.databinding.ItemPullBinding
import hr.fer.filip.model.Contributor
import hr.fer.filip.model.Issue
import hr.fer.filip.model.Pull
import kotlin.collections.ArrayList

class FragmentRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val recyclerList = ArrayList<Any>()
    private val TYPE_CONTRIBUTOR = 0
    private val TYPE_ISSUE = 1
    private val TYPE_PULL = 2

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
                val binding = ItemPullBinding.inflate(inflater, parent, false)
                return PullViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return recyclerList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ContributorViewHolder -> holder.bind(recyclerList[position] as Contributor)
            is IssueViewHolder -> holder.bind(recyclerList[position] as Issue)
            else -> (holder as PullViewHolder).bind(recyclerList[position] as Pull)
        }
    }

    fun updateList(newList : List<Any>) {
        val result = DiffUtil.calculateDiff(FragmentListCallBack(this.recyclerList,newList))
        this.recyclerList.clear()
        this.recyclerList.addAll(newList)
        result.dispatchUpdatesTo(this)

    }

    override fun getItemViewType(position: Int): Int {
        return when(recyclerList[position]) {
            is Contributor -> TYPE_CONTRIBUTOR
            is Issue -> TYPE_ISSUE
            else -> TYPE_PULL
        }
    }
}