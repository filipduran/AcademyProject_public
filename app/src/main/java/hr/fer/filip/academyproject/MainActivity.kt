package hr.fer.filip.academyproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.filip.academyproject.databinding.ActivityMainBinding
import hr.fer.filip.academyproject.ui.main.RecyclerAdapter
import hr.fer.filip.model.Repository

class MainActivity : AppCompatActivity(),
    RecyclerAdapter.OnItemClickListener {

    val recyclerAdapter =
        RecyclerAdapter(this)
    private val viewModel by viewModels<MainActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root


        setContentView(view)

        val manager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = recyclerAdapter

        viewModel.data.observe(this, Observer {
            recyclerAdapter.updateList(it)
        })

        viewModel.loadRepoDetailsList()

        binding.title.setText("Square repositories")

//        binding.button.setOnClickListener {
//            viewModel.loadRepoDetailsList()
//        }


    }

    override fun onItemClicked(repository: Repository) {
        DetailsActivity.newInstance(this, repository.name)
    }
}
