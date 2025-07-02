package com.gundogar.e_commerce_demo.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gundogar.e_commerce_demo.data.remote.ApiResult
import com.gundogar.e_commerce_demo.databinding.FragmentHomeBinding
import com.gundogar.e_commerce_demo.presentation.favorite.FavoriteViewModel
import com.gundogar.e_commerce_demo.data.remote.toFavoriteProduct
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeUiState()
        observeFavorites()
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                homeViewModel.products.collect { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            binding.shimmerContainer.startShimmer()
                            binding.shimmerContainer.visibility = View.VISIBLE
                            binding.rvProducts.visibility = View.GONE
                        }

                        is ApiResult.Success -> {
                            binding.shimmerContainer.stopShimmer()
                            binding.shimmerContainer.visibility = View.GONE
                            binding.rvProducts.visibility = View.VISIBLE
                            productAdapter.submitList(result.data.products)
                        }

                        is ApiResult.Error -> {
                            binding.shimmerContainer.stopShimmer()
                            binding.shimmerContainer.visibility = View.GONE
                            binding.rvProducts.visibility = View.GONE
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }


    private fun observeFavorites() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                favoriteViewModel.favorites.collect { favorites ->
                    Log.d("HomeFragment", "Favorites changed, updating adapter. Count: ${favorites.size}")
                    productAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(
            onItemClick = { product ->
                val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(product)
                findNavController().navigate(action)
            },
            onFavoriteClick = { product ->
                favoriteViewModel.toggleFavorite(product.toFavoriteProduct())
            },
            isFavorite = { productId -> // Higher-order function
                favoriteViewModel.isFavorite(productId)
            }
        )
        binding.rvProducts.adapter = productAdapter
    }
}