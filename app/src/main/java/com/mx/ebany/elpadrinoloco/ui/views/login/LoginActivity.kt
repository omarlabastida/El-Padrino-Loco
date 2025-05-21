package com.mx.ebany.elpadrinoloco.ui.views.login

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.databinding.ActivityLoginBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}