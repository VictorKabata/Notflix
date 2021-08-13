package com.vickikbt.notflix.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.play.core.splitinstall.*
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ActivityMainBinding
import com.vickikbt.notflix.ui.fragments.ProgressBottomSheetFragment
import com.vickikbt.notflix.util.hide
import com.vickikbt.notflix.util.show
import timber.log.Timber

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    //private lateinit var installMonitor: DynamicInstallMonitor
    //private lateinit var dynamicExtras: DynamicExtras

    private lateinit var splitInstallManager: SplitInstallManager
    private lateinit var splitInstallStateUpdatedListener: SplitInstallStateUpdatedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Notflix)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        //binding.bottomNav.setupWithNavController(navController)

        splitInstallManager = SplitInstallManagerFactory.create(this)
        //globalSplitInstallManager = GlobalSplitInstallManagerFactory.create(this)

        initUI()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label == "MovieDetailsFragment") binding.bottomNav.hide()
            else binding.bottomNav.show()

            Timber.e("Destination: ${destination.label}")
        }


    }

    private fun initUI() {
        binding.bottomNav.setOnNavigationItemSelectedListener(this)

        splitInstallStateUpdatedListener = SplitInstallStateUpdatedListener { state ->
            showProgressBottomSheet(state)
        }
    }


    private fun installDynamicModule(moduleName: String, fragmentId: Int) {
        if (splitInstallManager.installedModules.contains(moduleName)) { //Module already installed
            navController.navigate(fragmentId)
            return
        } else { //Install module
            splitInstallManager.registerListener(splitInstallStateUpdatedListener)

            val splitInstallRequest = SplitInstallRequest.newBuilder()
                .addModule(moduleName)
                .build()

            splitInstallManager.startInstall(splitInstallRequest)
                .addOnCompleteListener { }
                .addOnSuccessListener { }
                .addOnFailureListener { }
        }
    }

    private fun showProgressBottomSheet(state: SplitInstallSessionState) {
        val progressBottomSheet = ProgressBottomSheetFragment(state = state)
        progressBottomSheet.show(supportFragmentManager, "Progress Bottom Sheet")
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_fragment -> {
                navController.navigate(R.id.home_fragment)
                return true
            }
            R.id.favorites_fragment -> {

                installDynamicModule(
                    moduleName = getString(R.string.title_favorites),
                    fragmentId = R.id.favorites_fragment
                )

                return true
            }
            R.id.settings_fragment -> {
                navController.navigate(R.id.settings_fragment)
                return true
            }
        }

        return false
    }

    override fun onResume() {
        splitInstallManager.registerListener(splitInstallStateUpdatedListener)
        super.onResume()
    }

    override fun onDestroy() {
        _binding = null
        splitInstallManager.unregisterListener(splitInstallStateUpdatedListener)
        //globalSplitInstallManager.unregisterListener(globalInstallListener)
        super.onDestroy()
    }


}