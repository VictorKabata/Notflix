package com.company.favorites.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.company.favorites.R
import com.company.favorites.databinding.FragmentFavoritesBinding
import com.company.favorites.di.loadFavoritesModule
import com.company.favorites.ui.adapters.FavoriteMoviesRecyclerviewAdapter
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.vickikbt.domain.models.Movie
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoritesViewModel by viewModel()
    private fun injectFeatures() = loadFavoritesModule

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentFavoritesBinding.bind(view)
        injectFeatures()

        initUI()

    }

    //ToDo: Remove boiler plate code
    private fun initUI() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.favoriteMovies.collect { uiState ->
                        when (uiState) {
                            is FavoritesViewModel.FavoritesUiState.Error -> showError(uiState.error)
                            FavoritesViewModel.FavoritesUiState.Loading -> Timber.e("Loading favorites")
                            is FavoritesViewModel.FavoritesUiState.Success -> showFavoriteMovies(
                                uiState.movies
                            )
                        }
                    }
                }
            }
        }
    }

    private fun showError(errorMessage: String) {
        Timber.e("An error occurred: $errorMessage")
    }

    private fun showFavoriteMovies(favorites: List<Movie>) {
        binding.recyclerviewFavoriteMovies.adapter =
            FavoriteMoviesRecyclerviewAdapter(favorites) { movie ->
                val action = FavoritesFragmentDirections.favoritesToDetails(
                    movieId = movie.id!!,
                    cacheId = movie.cacheId!!
                )
                findNavController().navigate(action)
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}