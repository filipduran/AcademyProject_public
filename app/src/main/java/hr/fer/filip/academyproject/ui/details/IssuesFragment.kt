package hr.fer.filip.academyproject.ui.details

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.filip.academyproject.DetailsActivityViewModel
import hr.fer.filip.academyproject.databinding.FragmentLayoutBinding

class IssuesFragment : Fragment() {

    private lateinit var repoID: String
    private lateinit var orgName : String
    private val viewModel by activityViewModels<DetailsActivityViewModel>()
    private var timeOnResume = System.currentTimeMillis()
    private var timeOnPause = System.currentTimeMillis()
    private var firstTime = true

    val handler = Handler()
    private var runnable = object : Runnable {
        override fun run() {
            viewModel.getIssuesList(orgName, repoID)
            handler.postDelayed(this, 15000)
        }
    }


    companion object {
        fun newInstance(orgName: String, repoID: String): IssuesFragment {
            val fragment = IssuesFragment()
            val args = Bundle()
            args.putString("orgName", orgName)
            args.putString("repoID", repoID)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            repoID = requireArguments().getString("orgName").toString()
            orgName = requireArguments().getString("repoID").toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentLayoutBinding.inflate(inflater, container, false)
        val recyclerAdapter = FragmentRecyclerAdapter()

        val manager = LinearLayoutManager(this.context)
        binding.fragmentRecycler.layoutManager = manager
        binding.fragmentRecycler.adapter = recyclerAdapter

        binding.swipeRefresh.setOnRefreshListener {
            Log.d("xxxxxxxxxxxx", "Called manual refresh")
            handler.removeCallbacksAndMessages(null)
            handler.post(runnable)
        }

        viewModel.repoDetails.observe(viewLifecycleOwner, Observer {
            Log.d("xxxxxxxxxxxx", "Entered IssuesFragment observer")
            repoID = it.name
            orgName = it.owner.login
        })

        viewModel.issueList.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.updateList(it)
            if (binding.swipeRefresh.isRefreshing) {
                binding.swipeRefresh.isRefreshing = false
            }
            binding.fragmentRecycler.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        })


        return binding.root

    }

override fun onResume() {
        super.onResume()
        timeOnResume = System.currentTimeMillis()
        val timeElapsed = timeOnResume - timeOnPause
        if (firstTime || timeElapsed > 15000) {
            handler.post(runnable)
            if (firstTime) {
                firstTime = false
            }
        } else {
            handler.postDelayed(runnable, 15000 - timeElapsed)
        }
    }

    override fun onPause() {
        super.onPause()
        timeOnPause = System.currentTimeMillis()
        handler.removeCallbacksAndMessages(null)


    }
}