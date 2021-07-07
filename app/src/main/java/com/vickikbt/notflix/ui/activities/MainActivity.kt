package com.vickikbt.notflix.ui.activities

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Notflix)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        /*navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.details_fragment -> {
                    binding.bottomNav.visibility = GONE
                }
                else -> binding.bottomNav.visibility = VISIBLE
            }
        }*/

    }
}