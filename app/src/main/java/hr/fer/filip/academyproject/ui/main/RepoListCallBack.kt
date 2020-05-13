package hr.fer.filip.academyproject.ui.main

import androidx.recyclerview.widget.DiffUtil
import hr.fer.filip.model.Repository

class RepoListCallBack (oldList: List<Repository>, newList: List<Repository>) : DiffUtil.Callback() {
    private val oldList = oldList.toList()
    private val newList = newList.toList()

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList[oldItemPosition].name.equals(newList[newItemPosition].name)) {
            return true
        }
        return false
    }

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return false
    }
}