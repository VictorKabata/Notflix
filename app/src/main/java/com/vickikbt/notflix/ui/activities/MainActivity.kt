package com.vickikbt.notflix.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ActivityMainBinding
import com.vickikbt.notflix.util.hide
import com.vickikbt.notflix.util.show
import org.koin.android.ext.android.inject
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private val analytics: FirebaseAnalytics by inject()

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

            analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
                param(FirebaseAnalytics.Param.SCREEN_NAME, destination.label.toString())
            }

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