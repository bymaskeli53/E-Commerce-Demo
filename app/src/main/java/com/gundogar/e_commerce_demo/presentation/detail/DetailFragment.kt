package com.gundogar.e_commerce_demo.presentation.detail

import android.os.Bundle
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
import androidx.navigation.fragment.navArgs
import coil.load
import com.gundogar.e_commerce_demo.R
import com.gundogar.e_commerce_demo.databinding.FragmentDetailBinding
import com.gundogar.e_commerce_demo.core.util.startFlyToCartAnimation
import com.gundogar.e_commerce_demo.core.util.toFullImageUrl
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: DetailFragmentArgs by navArgs()

    private val viewModel: DetailViewModel by viewModels()

    private var currentQuantity = 1
    private val minQuantity = 1
    private val maxQuantity = 99

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupProductDetails()
        setupQuantityControls()
        setupAddToBasketButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupProductDetails() {
        val selectedProduct = navigationArgs.product

        with(binding) {
            tvProductName.text = selectedProduct.name
            tvProductBrand.text = selectedProduct.brand
            tvProductPrice.text = selectedProduct.price.toString() + " TL"

            loadProductImage(selectedProduct.image)
        }
    }

    private fun setupQuantityControls() {
        binding.btnDecrement.setOnClickListener {
            decrementQuantity()
        }

        binding.btnIncrement.setOnClickListener {
            incrementQuantity()
        }
    }

    private fun setupAddToBasketButton() {
        binding.btnAddToBasket.setOnClickListener {
            addProductToBasket()

            startFlyToCartAnimation(
                binding.ivProductImage,
                requireActivity().window.decorView.rootView as ViewGroup,
                requireActivity().findViewById(R.id.btn_add_to_basket)
            )
        }
    }


    private fun loadProductImage(imageUrl: String) {
        binding.ivProductImage.load(imageUrl.toFullImageUrl()) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_error_placeholder)
            crossfade(enable = true)
            crossfade(durationMillis = 300)
        }
    }

    private fun decrementQuantity() {
        if (currentQuantity > minQuantity) {
            currentQuantity--
            binding.tvQuantity.text = currentQuantity.toString()
            updateButtonStates()
        }
    }

    private fun incrementQuantity() {
        if (currentQuantity < maxQuantity) {
            currentQuantity++
            binding.tvQuantity.text = currentQuantity.toString()
            updateButtonStates()
        }
    }

    private fun updateButtonStates() {
        binding.btnDecrement.isEnabled = currentQuantity > minQuantity
        binding.btnIncrement.isEnabled = currentQuantity < maxQuantity
    }

    private fun addProductToBasket() {
        val selectedProduct = navigationArgs.product
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.addToBasket(
                    ad = selectedProduct.name,
                    resim = selectedProduct.image,
                    kategori = selectedProduct.category,
                    fiyat = selectedProduct.price.toInt(),
                    marka = selectedProduct.brand,
                    siparisAdeti = currentQuantity,
                    kullaniciAdi = "muhammet_gundogar"
                )
                findNavController().popBackStack()
                showToast("Ürün sepete eklendi.")
            }

        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}