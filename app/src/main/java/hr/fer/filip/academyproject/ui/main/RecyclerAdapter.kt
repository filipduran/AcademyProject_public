package hr.fer.filip.academyproject.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import hr.fer.filip.academyproject.databinding.ReposDetailsBinding
import hr.fer.filip.model.Repository

class RecyclerAdapter(val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val reposDetailsList = ArrayList<Repository>()

    interface OnItemClickListener {
        fun onItemClicked(repository : Repository)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ReposDetailsBinding.inflate(inflater, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reposDetailsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RepositoryViewHolder).bind(reposDetailsList[position],itemClickListener)
    }

    fun updateList(newList : List<Repository>) {
        val result = DiffUtil.calculateDiff(
            RepoListCallBack(
                this.reposDetailsList,
                newList
            )
        )
        this.reposDetailsList.clear()
        this.reposDetailsList.addAll(newList)
        result.dispatchUpdatesTo(this)
    }
}