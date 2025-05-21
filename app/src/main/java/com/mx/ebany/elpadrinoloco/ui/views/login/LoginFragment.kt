package com.mx.ebany.elpadrinoloco.ui.views.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.databinding.FragmentLoginBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setInitialValues()
        setObservers()
        setListeners()

    }

    private fun setInitialValues() {
    }

    private fun setObservers() {
    }

    private fun setListeners() {

        binding.btnClientMenu.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainActivity)
        }

        binding.btAdmin.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_adminActivity)
        }
    }


}