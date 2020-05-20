package hr.fer.filip.academyproject.ui.main

import androidx.recyclerview.widget.RecyclerView
import hr.fer.filip.academyproject.databinding.ReposDetailsBinding
import hr.fer.filip.model.RepoDetails

class RepositoryViewHolder(val binding : ReposDetailsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository : RepoDetails, clickListener: RecyclerAdapter.OnItemClickListener) {
        binding.repoName.text = repository.name
        binding.repoDescription.text = repository.description

        binding.cardView.setOnClickListener {
            clickListener.onItemClicked(repository)
        }
    }
}