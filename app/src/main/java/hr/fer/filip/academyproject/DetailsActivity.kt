package hr.fer.filip.academyproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import hr.fer.filip.academyproject.databinding.ActivityDetailsBinding
import hr.fer.filip.academyproject.ui.details.ContributorsFragment
import hr.fer.filip.academyproject.ui.details.IssuesFragment
import hr.fer.filip.academyproject.ui.details.PullsFragment
import hr.fer.filip.academyproject.ui.details.SectionsPagerAdapter

class DetailsActivity : FragmentActivity() {

    companion object {
        fun newInstance(context: Context, repoID: String, ownerName: String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("repoID", repoID)
            intent.putExtra("ownerName", ownerName)
            context.startActivity(intent)
        }
    }

    private lateinit var repoID: String
    private lateinit var ownerName: String

    private val viewModel by viewModels<DetailsActivityViewModel>()

    val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            viewModel.getRepoDetails(ownerName, repoID)
            handler.postDelayed(this, 15000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        repoID = intent.getSerializableExtra("repoID") as String
        ownerName = intent.getSerializableExtra("ownerName") as String

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager

        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        viewModel.repoDetails.observe(this, Observer {
            repoID = it.name
            ownerName = it.owner.login
        })

        viewModel.headerResponses.observe(this, Observer {
            val fragmentList = ArrayList<Fragment>()
            val titleList = ArrayList<String>()
            for (position in it.indices) {
                if (it[position] == 200)  {
                    when (position) {
                        0 -> {
                            fragmentList.add(ContributorsFragment.newInstance(ownerName, repoID))
                            titleList.add("Contributors")
                        }
                        1 -> {
                            fragmentList.add(IssuesFragment.newInstance(ownerName, repoID))
                            titleList.add("Issues")
                        }
                        else -> {
                            fragmentList.add(PullsFragment.newInstance(ownerName, repoID))
                            titleList.add("Pulls")
                        }
                    }
                }
            }
            sectionsPagerAdapter.addFragments(fragmentList, titleList)
            viewPager.offscreenPageLimit = fragmentList.size - 1
            viewPager.adapter = sectionsPagerAdapter
        })

        viewModel.headResponses(ownerName, repoID)

    }

    override fun onResume() {
        super.onResume()

        handler.post(runnable)

    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacksAndMessages(null)
    }
}