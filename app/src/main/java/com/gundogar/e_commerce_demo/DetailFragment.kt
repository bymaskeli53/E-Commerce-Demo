package com.gundogar.e_commerce_demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.gundogar.e_commerce_demo.databinding.FragmentDetailBinding

/**
 * Ürün detaylarını gösteren fragment sınıfı
 */
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val navigationArgs: DetailFragmentArgs by navArgs()

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * Ürün bilgilerini UI elementlerine bağlar
     */
    private fun setupProductDetails() {
        val selectedProduct = navigationArgs.product

        with(binding) {
            tvProductName.text = selectedProduct.name
            tvProductBrand.text = selectedProduct.brand
            tvProductPrice.text = selectedProduct.price.toString() + " TL"

            loadProductImage(selectedProduct.image)
        }
    }

    /**
     * Ürün resmini yükler ve görüntüler
     */
    private fun loadProductImage(imageUrl: String) {
        binding.ivProductImage.load(imageUrl.toFullImageUrl()) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
            crossfade(enable = true)
            crossfade(durationMillis = 300)
        }
    }

    /**
     * Fiyatı formatlar
     */
    private fun formatPrice(price: Double): String {
        return "$${"%.2f".format(price)}"
    }
}