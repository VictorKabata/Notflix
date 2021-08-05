package com.company.favorites.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.company.favorites.R
import com.company.favorites.databinding.FragmentFavoritesBinding
import com.company.favorites.di.loadFavoritesModule
import com.company.favorites.ui.adapters.FavoriteMoviesRecyclerviewAdapter
import com.vickikbt.notflix.util.StateListener
import com.vickikbt.notflix.util.hide
import com.vickikbt.notflix.util.show
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoritesFragment : Fragment(R.layout.fragment_favorites), StateListener {

    private var _binding:FragmentFavoritesBinding?=null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModel()
    private fun injectFeatures() = loadFavoritesModule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding= FragmentFavoritesBinding.bind(view)
        injectFeatures()
        viewModel.stateListener=this

        initUI()

    }

    private fun initUI(){
        viewModel.favoriteMovies.observe(viewLifecycleOwner){favorites->
            if (favorites.isNullOrEmpty()){
                binding.recyclerviewFavoriteMovies.hide()
                binding.layoutEmpty.root.show()
            }else{
                binding.recyclerviewFavoriteMovies.show()
                binding.layoutEmpty.root.hide()

                binding.recyclerviewFavoriteMovies.adapter=FavoriteMoviesRecyclerviewAdapter(favorites){movie->
                    val action = FavoritesFragmentDirections.favoritesToDetails(movieId = movie.id!!, cacheId=movie.cacheId!!)
                    findNavController().navigate(action)
                }
            }
        }
    }

    override fun onLoading() {
        Timber.e("Loading favorites...")
    }

    override fun onSuccess(message: String) {

    }

    override fun onError(message: String?) {
        Timber.e("Error fetching favorites: $message")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}