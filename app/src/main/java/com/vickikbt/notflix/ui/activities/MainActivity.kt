package com.vickikbt.notflix.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Notflix)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //binding.bottomNav.setupWithNavController(navController)

        /*navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.details_fragment -> {
                    binding.bottomNav.visibility = GONE
                }
                else -> binding.bottomNav.visibility = VISIBLE
            }
        }*/

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}