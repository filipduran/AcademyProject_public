package hr.fer.filip.academyproject.ui.details

import androidx.recyclerview.widget.DiffUtil
import hr.fer.filip.model.Contributor
import hr.fer.filip.model.Issue

class FragmentListCallBack(oldList: List<Any>, newList: List<Any>) : DiffUtil.Callback() {
    private val oldList = oldList.toList()
    private val newList = newList.toList()


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldList[oldItemPosition] is Contributor) {
            if ((oldList[oldItemPosition] as Contributor).login.equals((newList[newItemPosition] as Contributor).login)) {
                return true
            }
        }
        if (oldList[oldItemPosition] is Issue) {
            if ((oldList[oldItemPosition] as Issue).title.equals((newList[newItemPosition] as Issue).title)) {
                return true
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