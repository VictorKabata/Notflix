package com.vickikbt.notflix.ui.activities

import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jeppeman.globallydynamic.globalsplitinstall.*
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ActivityMainBinding
import timber.log.Timber

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

        binding.bottomNav.setOnClickListener {
            Timber.e("Clicked bottom nav")
        }


    }

    private fun initDestinationListener(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.label) {
                "Home" -> {
                    Timber.e("Home fragment")
                }
                "Search" -> {
                    installDynamicModule(moduleName = "search")
                }
                "Favorites" -> {
                    Timber.e("Favorites fragment")
                    installDynamicModule(moduleName = "favorites")
                }
                "Settings" -> {
                    Timber.e("Settings fragment")
                }
                "Details" -> {
                    Timber.e("Details fragment")
                    binding.bottomNav.visibility=GONE
                }
            }
        }
    }

    private fun installDynamicModule(moduleName: String) {
        val listener = GlobalSplitInstallUpdatedListener { state ->
            when (state.status()) {
                GlobalSplitInstallSessionStatus.DOWNLOADING -> Timber.e("Downloading module")
                GlobalSplitInstallSessionStatus.FAILED -> Timber.e("Downloading module failed")
                GlobalSplitInstallSessionStatus.DOWNLOADED -> Timber.e("Module downloaded")
                GlobalSplitInstallSessionStatus.INSTALLED -> Timber.e("Module installed")
            }
        }

        val request = GlobalSplitInstallRequest.newBuilder()
            .addModule(moduleName)
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