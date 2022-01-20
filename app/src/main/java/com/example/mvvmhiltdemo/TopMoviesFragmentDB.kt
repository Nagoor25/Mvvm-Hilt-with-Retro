package com.example.mvvmhiltdemo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmhiltdemo.databinding.FragmentTopMoviesBinding
import com.example.mvvmhiltdemo.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TopMoviesFragmentDB  : Fragment(R.layout.fragment_top_movies) {
  lateinit var binding:FragmentTopMoviesBinding
  @Inject
  lateinit var madapter: MovieAdapter
  private val viewModel:MovieViewModel by viewModels()
/*init {
    viewModel.getAllTopMoviesFromDB()
}
  */
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         binding=FragmentTopMoviesBinding.bind(view)
     //    viewModel.getAllTopMoviesFromDB()
        setRecycleView()

      binding.apply {
          ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
              0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
          ){
              override fun onMove(
                  recyclerView: RecyclerView,
                  viewHolder: RecyclerView.ViewHolder,
                  target: RecyclerView.ViewHolder
              ): Boolean {
                  TODO("Not yet implemented")
              }

              override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                  val movie=madapter.differ.currentList.get(viewHolder.adapterPosition)
                  viewModel.deleteMovie(movie)

              }

          }
          ).attachToRecyclerView(recyclerView)
      }

      viewModel.getAllTopMoviesFromDB().observe(viewLifecycleOwner, Observer {res->
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