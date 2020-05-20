package hr.fer.filip.academyproject.ui.details

import androidx.recyclerview.widget.RecyclerView
import hr.fer.filip.academyproject.databinding.ItemPullBinding
import hr.fer.filip.model.Pull

class PullViewHolder(val binding : ItemPullBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pull : Pull) {
        binding.pullTitle.text = pull.title
    }
}