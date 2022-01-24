package com.example.mvvmhiltdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmhiltdemo.databinding.FragmentTopMoviesBinding
import com.example.mvvmhiltdemo.viewmodel.MovieViewModel
import com.google.android.material.snackbar.Snackbar
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
      madapter.setOnMyItemClickListener {
          val bundle=Bundle().apply {
              putSerializable("movie",it)
          }
          findNavController().navigate(R.id.action_topMoviesFragment_to_movieDetailFragment,bundle)
      }
      viewModel.respTopRatedMovie.observe(viewLifecycleOwner, Observer {res->
          when(res) {
              is Resource.Success ->{
                  hideProgressBar()
                  res?.let { serRes->
                      madapter.differ.submitList(serRes.data?.results)
                  }
              }
              is Resource.Loading->
                  showProgressBar()
              is Resource.Error ->
                  Snackbar.make(binding.root,"${res.message}",Snackbar.LENGTH_SHORT).show()

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
    private fun hideProgressBar() {
        binding.progressBar.visibility=View.INVISIBLE
    }
    private fun showProgressBar() {
        binding.progressBar.visibility=View.VISIBLE
    }


}