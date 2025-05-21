package com.mx.ebany.elpadrinoloco.ui.views.AdminMenu

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.databinding.ActivityAdminBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdminActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdminBinding
    private val viewModel: MainViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_admin) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_menu_admin)
        bottomNav.setupWithNavController(navController)
    }
}