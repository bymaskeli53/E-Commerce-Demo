package com.gundogar.e_commerce_demo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gundogar.e_commerce_demo.databinding.FragmentBasketBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class BasketFragment : Fragment() {

    private val viewModel: BasketViewModel by viewModels()

    private val basketAdapter = BasketAdapter {
        viewModel.deleteBasketItems(it.basketId)
    }

    private lateinit var binding: FragmentBasketBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        completeOrder()

        binding.rvBasket.adapter = basketAdapter
        observeBasketUiState()


    }

    private fun observeBasketUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.basketItems.collect { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            binding.lottieLoading.visibility = View.VISIBLE
                            binding.rvBasket.visibility = View.GONE
                            binding.layoutSummary.visibility = View.GONE
                            binding.ivEmptyBasket.visibility = View.GONE
                            binding.tvEmptyList.visibility = View.GONE
                        }
                        is ApiResult.Success -> {
                            binding.lottieLoading.visibility = View.GONE
                            binding.rvBasket.visibility = View.VISIBLE
                            binding.layoutSummary.visibility = View.VISIBLE
                            binding.ivEmptyBasket.visibility = View.GONE
                            binding.tvEmptyList.visibility = View.GONE
                            basketAdapter.submitList(result.data)
                            setTotalPrice(result.data.sumOf { it.price })
                        }
                        is ApiResult.Error -> {
                            binding.lottieLoading.visibility = View.GONE
                            binding.rvBasket.visibility = View.GONE
                            binding.layoutSummary.visibility = View.GONE
                            binding.ivEmptyBasket.visibility = View.VISIBLE
                            binding.tvEmptyList.visibility = View.VISIBLE
                            Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun setTotalPrice(totalPrice: Int) {
        binding.tvTotalPrice.text = buildString {
            append("Toplam Tutar: ")
            append(totalPrice.toString())
            append(" TL")
        }
    }

    private fun completeOrder() {
        binding.btnConfirmBasket.setOnClickListener {
            showSuccessDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getBasketItems()
    }

    private fun showSuccessDialog() {
        val dialogView = layoutInflater.inflate(R.layout.success_order_dialog, null)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialogView.findViewById<Button>(R.id.btnDialogOk).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

}