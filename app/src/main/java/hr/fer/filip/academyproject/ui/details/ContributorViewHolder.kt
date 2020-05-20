package hr.fer.filip.academyproject.ui.details

import androidx.recyclerview.widget.RecyclerView
import hr.fer.filip.academyproject.databinding.ItemContributorBinding
import hr.fer.filip.model.Contributor

class ContributorViewHolder(val binding: ItemContributorBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contributor: Contributor) {
        binding.contributorsName.text = contributor.login + "   -   " + contributor.contributions + " contributions"
    }

}