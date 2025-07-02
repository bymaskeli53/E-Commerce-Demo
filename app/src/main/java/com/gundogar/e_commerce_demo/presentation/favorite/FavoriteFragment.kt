package com.gundogar.e_commerce_demo.presentation.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gundogar.e_commerce_demo.databinding.FragmentFavoriteBinding
import com.gundogar.e_commerce_demo.core.util.launchWhenStarted
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel: FavoriteViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFavoriteProducts()


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observeFavoriteProducts() {
        launchWhenStarted {
            favoriteViewModel.favorites.collect { products ->
                binding.rvFavorites.adapter = FavoriteAdapter(products)
            }
        }

    }

    override fun onResume() {
        super.onResume()
        favoriteViewModel.loadFavorites()
    }
}
