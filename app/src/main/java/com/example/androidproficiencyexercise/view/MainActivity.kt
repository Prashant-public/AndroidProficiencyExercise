package com.example.androidproficiencyexercise.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidproficiencyexercise.R
import com.example.androidproficiencyexercise.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ListViewModel
    private val canadaListAdapter = CanadaListAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        canadaList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = canadaListAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            viewModel.refresh()
        }

        observeViewModel()
    }

    private fun observeViewModel() {

        viewModel.canada.observe(this, Observer { canada ->
            canada?.let {
                canadaList.visibility = View.VISIBLE
                canadaListAdapter.updateCanadaData(it.aboutCanadaArray)
                supportActionBar?.setTitle(it.title)
            }
        })

        viewModel.canadaLoadError.observe(this, Observer { isError ->
            isError?.let {
                list_error.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    canadaList.visibility = View.GONE
                }
            }
        })
    }
}
