package hr.fer.filip.academyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.filip.academyproject.databinding.ActivityMainBinding
import hr.fer.filip.academyproject.ui.main.RecyclerAdapter
import hr.fer.filip.model.RepoDetails

class MainActivity : AppCompatActivity(), RecyclerAdapter.OnItemClickListener {

    private val recyclerAdapter = RecyclerAdapter(this)
    private val viewModel by viewModels<MainActivityViewModel>()
    private lateinit var binding : ActivityMainBinding

    private val ORGANIZATION = "Square"

    val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            viewModel.loadRepoDetailsList(ORGANIZATION)
            handler.postDelayed(this, 15000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = recyclerAdapter

        viewModel.repoList.observe(this, Observer {
            recyclerAdapter.updateList(it)
        })


        binding.title.setText(ORGANIZATION + " repositories")


    }

    override fun onItemClicked(repository: RepoDetails) {
        DetailsActivity.newInstance(this, repository.name, repository.owner.login)
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
