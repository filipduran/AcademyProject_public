package hr.fer.filip.academyproject.ui.details

import androidx.recyclerview.widget.DiffUtil
import hr.fer.filip.model.Contributor
import hr.fer.filip.model.Issue
import hr.fer.filip.model.Pull

class FragmentListCallBack(oldList: List<Any>, newList: List<Any>) : DiffUtil.Callback() {
    private val oldList = oldList.toList()
    private val newList = newList.toList()


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        when (oldItem) {
            is Contributor -> {
                if (oldItem.login.equals((newItem as Contributor).login)) {
                    return true
                }
            }
            is Issue -> {
                if (oldItem.title.equals((newItem as Issue).title)) {
                    return true
                }
            }
            else -> {
                if ((oldItem as Pull).title.equals((newItem as Pull).title)) {
                    return true
                }
            }
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