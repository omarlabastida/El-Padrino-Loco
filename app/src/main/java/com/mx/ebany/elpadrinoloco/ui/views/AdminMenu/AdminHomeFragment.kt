package com.mx.ebany.elpadrinoloco.ui.views.AdminMenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.databinding.FragmentAdminHomeBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminHomeFragment : Fragment() {

    private lateinit var binding: FragmentAdminHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAdminHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}