package com.mx.ebany.elpadrinoloco.ui.views.AdminMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.databinding.FragmentAdminEmployeesBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AdminEmployeesFragment : Fragment() {

    private lateinit var binding: FragmentAdminEmployeesBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminEmployeesBinding.inflate(inflater, container, false)
        return binding.root
    }


}