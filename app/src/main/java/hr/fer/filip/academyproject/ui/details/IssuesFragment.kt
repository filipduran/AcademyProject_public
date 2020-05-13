package hr.fer.filip.academyproject.ui.details

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.filip.academyproject.DetailsActivityViewModel
import hr.fer.filip.academyproject.databinding.FragmentLayoutBinding
import hr.fer.filip.model.Issue

class IssuesFragment : Fragment() {

    private lateinit var issuesList: ArrayList<Issue>
    private lateinit var repoID: String
    private val viewModel by viewModels<DetailsActivityViewModel>()
    private var timeOnResume = System.currentTimeMillis()
    private var timeOnPause = System.currentTimeMillis()
    private var firstTime = true

    val handler = Handler()
    private var runnable = object : Runnable {
        override fun run() {
            viewModel.getIssuesList(repoID)
            handler.postDelayed(this, 15000)
        }
    }


    companion object {
        fun newInstance(param1: ArrayList<Issue>, param2: String): IssuesFragment {
            val fragment = IssuesFragment()
            val args = Bundle()
            args.putSerializable("param1", param1)
            args.putString("param2", param2)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            issuesList = requireArguments().getSerializable("param1") as ArrayList<Issue>
            repoID = requireArguments().getString("param2").toString()
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

        viewModel.data.observe(viewLifecycleOwner, Observer {
            repoID = it.name
        })

        viewModel.issueList.observe(viewLifecycleOwner, Observer {
            recyclerAdapter.updateList(it)
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
            Handler().postDelayed({
                viewModel.getIssuesList(repoID)
            }, 15000 - timeElapsed)
            handler.postDelayed(runnable, 15000)
        }
    }

    override fun onPause() {
        super.onPause()
        timeOnPause = System.currentTimeMillis()
        handler.removeCallbacks(runnable)


    }
}