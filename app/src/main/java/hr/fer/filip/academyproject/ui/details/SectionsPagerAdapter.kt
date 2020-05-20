package hr.fer.filip.academyproject.ui.details

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()


    override fun getItem(position: Int): Fragment {
        return fragmentList[position]

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size

    }

    fun addFragments(fragmentList: List<Fragment>, titleList: List<String>) {
        this.fragmentList.addAll(fragmentList)
        this.titleList.addAll(titleList)
    }
}