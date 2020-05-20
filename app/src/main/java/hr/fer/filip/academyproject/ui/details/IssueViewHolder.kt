package hr.fer.filip.academyproject.ui.details

import androidx.recyclerview.widget.RecyclerView
import hr.fer.filip.academyproject.databinding.ItemIssueBinding
import hr.fer.filip.model.Issue

class IssueViewHolder(val binding : ItemIssueBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(issue : Issue) {
        binding.issueTitle.text = issue.title
    }
}