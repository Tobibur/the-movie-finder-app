package com.tobibur.themoviefinderapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.tobibur.themoviefinderapp.R
import com.tobibur.themoviefinderapp.data.repository.Outcome
import kotlinx.android.synthetic.main.movies_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment : Fragment() {

    companion object {
        fun newInstance() = MoviesFragment()
        private const val TAG = "MoviesFragment"
    }

    private val moviesViewModel: MoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        moviesViewModel.popularMovies(1).observe(viewLifecycleOwner, Observer { outcome->
            progress_movies.visibility = View.GONE
            when(outcome){
                is Outcome.Success -> {
                    Log.d(TAG, "onActivityCreated: ${outcome.data}")
                    Toast.makeText( activity!!, "Success!", Toast.LENGTH_SHORT).show()
                }
                is Outcome.Failure -> {
                    Toast.makeText( activity!!, outcome.e.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}
