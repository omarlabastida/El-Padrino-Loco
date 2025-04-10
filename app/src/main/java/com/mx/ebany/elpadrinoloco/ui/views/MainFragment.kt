package com.mx.ebany.elpadrinoloco.ui.views

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mx.ebany.elpadrinoloco.data.models.User
import com.mx.ebany.elpadrinoloco.databinding.FragmentMainBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInitialValues()
        setListeners()
        setObservers()
    }

    private fun setInitialValues() {
        viewModel.loadUsers()

    }

    private fun setListeners() {
        binding.btnSave.setOnClickListener {

            viewModel.addUserFirestore(
                User(
                nombre = "DAVID",
                edad = 30,
                apellidoPaterno = "GARCIA",
                apellidoMaterno = "GONZALEZ",
                sesion = "123456"

            )
            )
        }


    }

    private fun setObservers() {
        viewModel.dataUser.observe(requireActivity()) { user ->
            Log.d("MainActivity", "User: $user")
        }
    }


}