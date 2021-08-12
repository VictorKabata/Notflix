package com.vickikbt.notflix.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.dynamicfeatures.DynamicExtras
import androidx.navigation.dynamicfeatures.DynamicInstallMonitor
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.play.core.splitinstall.SplitInstallSessionState
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

    //private lateinit var globalSplitInstallManager: GlobalSplitInstallManager
    //private var globalSessionId = 0

    //private lateinit var globalInstallListener: GlobalSplitInstallUpdatedListener

    private lateinit var installMonitor : DynamicInstallMonitor
    private lateinit var dynamicExtras : DynamicExtras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Notflix)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding.bottomNav.setupWithNavController(navController)

        //globalSplitInstallManager = GlobalSplitInstallManagerFactory.create(this)

        initUI()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label == "MovieDetailsFragment") binding.bottomNav.hide()
            else binding.bottomNav.show()

            Timber.e("Destination: ${destination.label}")
        }


    }

    private fun initUI() {
        //binding.bottomNav.setOnNavigationItemSelectedListener(this)
    }


    /*private fun installDynamicModule(moduleName: String, fragmentId: Int) {
        val name = moduleName.substring(0).capitalize()

        globalInstallListener = GlobalSplitInstallUpdatedListener { state ->
            if (state.sessionId() == globalSessionId) {
                //val progressBottomSheetFragment = ProgressBottomSheetFragment(state)
                //progressBottomSheetFragment.show(supportFragmentManager, "Progress BottomSheet")
            }
        }

        val request = GlobalSplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()


        globalSplitInstallManager.registerListener(globalInstallListener)
        globalSplitInstallManager.startInstall(request)
            .addOnSuccessListener { sessionId -> globalSessionId = sessionId }
            .addOnFailureListener { exception ->
                when ((exception as GlobalSplitInstallException).errorCode) {
                    GlobalSplitInstallErrorCode.NETWORK_ERROR -> {
                        Timber.e("Network error downloading feature")
                    }

                    GlobalSplitInstallErrorCode.INSUFFICIENT_STORAGE -> {
                        Timber.e("Insufficient storage error downloading feature")
                    }
                }
            }

    }*/

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_fragment -> {
                navController.navigate(R.id.home_fragment)
                return true
            }
            R.id.favorites_fragment -> {
                installMonitor= DynamicInstallMonitor()
                dynamicExtras=DynamicExtras(installMonitor = installMonitor)

                monitorFeatureInstall()

                navController.navigate(R.id.favorites_fragment, null, null, dynamicExtras)

                return true
            }
            R.id.settings_fragment -> {
                navController.navigate(R.id.settings_fragment)
                return true
            }
        }

        return false
    }

    private fun monitorFeatureInstall() {
        installMonitor.status.observe(this) { state ->
            Timber.e("State: $state")

            showProgressBottomSheet(state)

            if (state.hasTerminalStatus()) installMonitor.status.removeObservers(this)
        }
    }

    private fun showProgressBottomSheet(state: SplitInstallSessionState){
        val progressBottomSheet=ProgressBottomSheetFragment(state = state)
        progressBottomSheet.show(supportFragmentManager, "Progress Bottom Sheet")
    }

    override fun onDestroy() {
        super.onDestroy()
        //globalSplitInstallManager.unregisterListener(globalInstallListener)
        _binding = null
    }


}