package com.vickikbt.notflix.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jeppeman.globallydynamic.globalsplitinstall.*
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ActivityMainBinding
import timber.log.Timber
import java.util.*

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var globalSplitInstallManager: GlobalSplitInstallManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Notflix)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        globalSplitInstallManager = GlobalSplitInstallManagerFactory.create(this)

        initDestinationListener(navController)

        initModuleInstall()

    }

    private fun initDestinationListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.label) {
                "favorites" -> {
                    Timber.e("Favourites fragment")
                }
            }
        }
    }

    private fun initModuleInstall() {
        Timber.e("initModuleInstall invoked")

        val listener = GlobalSplitInstallUpdatedListener { state ->
            when (state.status()) {
                GlobalSplitInstallSessionStatus.DOWNLOADING -> Timber.e("Downloading module")
                GlobalSplitInstallSessionStatus.FAILED -> Timber.e("Downloading module failed")
                GlobalSplitInstallSessionStatus.DOWNLOADED -> Timber.e("Module downloaded")
                GlobalSplitInstallSessionStatus.INSTALLED -> Timber.e("Module installed")
            }
        }

        val request = GlobalSplitInstallRequest.newBuilder()
            .addModule("favorites")
            //.addLanguage(Locale.ENGLISH)
            .build()

        globalSplitInstallManager.registerListener(listener)

        globalSplitInstallManager.startInstall(request)
            .addOnSuccessListener { sessionId -> Timber.e("Success downloading module: $sessionId") }
            .addOnFailureListener { exception -> Timber.e("Failure: ${exception.message}") }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}