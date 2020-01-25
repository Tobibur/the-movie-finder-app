package com.tobibur.themoviefinderapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.tobibur.pagination.PageListener
import com.tobibur.pagination.PaginationUtils
import com.tobibur.themoviefinderapp.R
import com.tobibur.themoviefinderapp.data.repository.Outcome
import kotlinx.android.synthetic.main.movies_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment(), PageListener {

    companion object {
        fun newInstance() = MoviesFragment()
        private const val TAG = "MoviesFragment"
    }

    private lateinit var moviesAdapter: MoviesAdapter
    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesAdapter = MoviesAdapter(mutableListOf())
        val gridLayoutManager = GridLayoutManager(activity, 3)
        recycler_movies.layoutManager = gridLayoutManager
        recycler_movies.setHasFixedSize(true)
        recycler_movies.adapter = moviesAdapter
        PaginationUtils.initPagination(
            recycler_movies, gridLayoutManager,
            this
        )

        getPopularMovies()
    }

    private fun getPopularMovies(page: Int = 1) {
        moviesViewModel.popularMovies(page).observe(viewLifecycleOwner, Observer { outcome ->
            progress_movies.visibility = View.GONE
            when (outcome) {
                is Outcome.Success -> {
                    Log.d(TAG, "onActivityCreated: ${outcome.data}")
                    outcome.data.totalPages
                    moviesAdapter.addMovies(outcome.data.results)
                }
                is Outcome.Failure -> {
                    Toast.makeText(activity!!, outcome.e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onPagination(page: Int) {
        getPopularMovies(page)
    }
}
