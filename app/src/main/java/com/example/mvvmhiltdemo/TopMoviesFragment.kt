package com.example.mvvmhiltdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmhiltdemo.databinding.FragmentTopMoviesBinding
import com.example.mvvmhiltdemo.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopMoviesFragment  : Fragment(R.layout.fragment_top_movies) {
  lateinit var binding:FragmentTopMoviesBinding
  @Inject
  lateinit var madapter: MovieAdapter
  private val viewModel:MovieViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding=FragmentTopMoviesBinding.bind(view)

        setRecycleView()
      viewModel.respTopRatedMovie.observe(viewLifecycleOwner, Observer {res->
          res?.let {
              madapter.differ.submitList(it)

          }

      })
    }

    private fun setRecycleView() {
        madapter=MovieAdapter()
        binding.recyclerView.apply {
            layoutManager=LinearLayoutManager(context)
            setHasFixedSize(true)
           adapter=madapter

        }
    }


}