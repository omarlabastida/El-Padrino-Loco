package com.mx.ebany.elpadrinoloco.ui.views.ClientMenu

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mx.ebany.elpadrinoloco.R
import com.mx.ebany.elpadrinoloco.data.models.User
import com.mx.ebany.elpadrinoloco.databinding.ActivityMainBinding
import com.mx.ebany.elpadrinoloco.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNav = findViewById<BottomNavigationView>(R.id.nav_menu)
        bottomNav.setupWithNavController(navController)

        setInitialValues()
        setListeners()
        setObservers()
    }

    private fun setInitialValues() {
    }
    private fun setObservers() {
        viewModel.dataUser.observe(this) { usersList ->
            setClients(usersList)
        }
    }
    private fun setListeners() {
    }


    private fun setClients(clients: List<User>){

        val clientsList = mutableListOf<String>()

        clients.forEach{
            clientsList.add(it.nombre)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, clientsList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.inToolbar.spClientName.adapter = adapter

        binding.inToolbar.spClientName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                viewModel.setActualClient(clients.filter { it.nombre == clients[i].nombre }.first())
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }
        }
    }



}