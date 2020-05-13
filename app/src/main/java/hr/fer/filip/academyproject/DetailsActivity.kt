package hr.fer.filip.academyproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import hr.fer.filip.academyproject.databinding.ActivityDetailsBinding
import hr.fer.filip.academyproject.ui.details.SectionsPagerAdapter

class DetailsActivity : FragmentActivity() {

    companion object {
        fun newInstance(context : Context, repoID : String) {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("repoID", repoID)
            context.startActivity(intent)
        }
    }

    private val viewModel by viewModels<DetailsActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        var repoID = intent.getSerializableExtra("repoID") as String

        viewModel.data.observe(this, Observer {
            repoID = it.name
        })

        val handler = Handler()
        val runnable = object : Runnable {
            override fun run() {
                viewModel.getRepoDetails(repoID)
                handler.postDelayed(this, 15000)
            }
        }
        handler.post(runnable)


        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager,repoID)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)



    }
}