package com.gundogar.e_commerce_demo.presentation.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gundogar.e_commerce_demo.R
import com.gundogar.e_commerce_demo.core.util.launchWhenStarted
import com.gundogar.e_commerce_demo.databinding.FragmentProfileBinding
import com.gundogar.e_commerce_demo.presentation.AuthActivity
import com.gundogar.e_commerce_demo.presentation.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadUserEmail()
        launchWhenStarted {
            viewModel.email.collect { email ->
                binding.tvUserEmail.text = email
            }
        }
        binding.layoutLogout.setOnClickListener {
            viewModel.signOut()
            requireActivity().finish()
            startActivity(Intent(requireContext(), AuthActivity::class.java))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}