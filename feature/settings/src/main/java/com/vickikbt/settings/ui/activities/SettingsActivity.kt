package com.vickikbt.settings.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.vickikbt.notflix.databinding.ActivityMainBinding
import com.vickikbt.settings.R
import com.vickikbt.settings.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private var _binding: ActivitySettingsBinding?=null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(com.vickikbt.notflix.R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        initUI()
    }

    private fun initUI(){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}