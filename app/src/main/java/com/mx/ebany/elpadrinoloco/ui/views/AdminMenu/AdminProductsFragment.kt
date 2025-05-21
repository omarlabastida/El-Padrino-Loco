package com.mx.ebany.elpadrinoloco.ui.views.AdminMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.databinding.FragmentAdminProductsBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminProductsFragment : Fragment() {

    private lateinit var binding: FragmentAdminProductsBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminProductsBinding.inflate(inflater, container, false)
        return binding.root
    }


}