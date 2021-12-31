package com.vickikbt.notflix.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Notflix)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)


        initUI()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label == "MovieDetailsFragment") binding.bottomNav.hide()
            else binding.bottomNav.show()

            Timber.e("Destination: ${destination.label}")
        }


    }

    private fun initUI() {

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}