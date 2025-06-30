package com.gundogar.e_commerce_demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gundogar.e_commerce_demo.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

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
    }


    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.products.collect { result ->
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
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_LONG).show()

                        }
                    }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(onItemClick = {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
            findNavController().navigate(action)
        })
        binding.rvProducts.adapter = productAdapter
    }

}