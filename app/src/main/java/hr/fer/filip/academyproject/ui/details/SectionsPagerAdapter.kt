package hr.fer.filip.academyproject.ui.details

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager,val repoID : String) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    private val TAB_TITLES = arrayOf(
        "Contributors",
        "Issues",
        "Pulls"
    )


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return ContributorsFragment.newInstance(ArrayList(),repoID)
            1 -> return IssuesFragment.newInstance(ArrayList(),repoID)
            else -> return ContributorsFragment.newInstance(ArrayList(),repoID)
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return 2

    }
}