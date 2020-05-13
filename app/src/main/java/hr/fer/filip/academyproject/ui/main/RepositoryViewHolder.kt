package hr.fer.filip.academyproject.ui.main

import androidx.recyclerview.widget.RecyclerView
import hr.fer.filip.academyproject.databinding.ReposDetailsBinding
import hr.fer.filip.academyproject.ui.main.RecyclerAdapter
import hr.fer.filip.model.Repository

class RepositoryViewHolder(val binding : ReposDetailsBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(repository : Repository, clickListener: RecyclerAdapter.OnItemClickListener) {
        binding.repoName.setText(repository.name)
        binding.repoDescription.setText(repository.description)

        binding.cardView.setOnClickListener {
            clickListener.onItemClicked(repository)
        }
    }
}