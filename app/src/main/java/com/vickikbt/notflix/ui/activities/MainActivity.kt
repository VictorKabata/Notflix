package com.vickikbt.notflix.ui.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jeppeman.globallydynamic.globalsplitinstall.*
import com.vickikbt.notflix.R
import com.vickikbt.notflix.databinding.ActivityMainBinding
import com.vickikbt.notflix.ui.fragments.ProgressBottomSheetFragment
import timber.log.Timber

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    private lateinit var globalSplitInstallManager: GlobalSplitInstallManager
    private var globalSessionId = 0

    private lateinit var globalInstallListener: GlobalSplitInstallUpdatedListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Notflix)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        globalSplitInstallManager = GlobalSplitInstallManagerFactory.create(this)

        initUI()


    }

    private fun initUI() {
        binding.bottomNav.setOnNavigationItemSelectedListener(this)

    }


    @SuppressLint("SwitchIntDef")
    private fun installDynamicModule(moduleName: String, fragmentId: Int) {
        val name = moduleName.substring(0).capitalize()

        globalInstallListener = GlobalSplitInstallUpdatedListener { state ->
            if (state.sessionId() == globalSessionId) {
                Timber.e("State in listener: ${state.status()}")
                //val progressBottomSheetFragment = ProgressBottomSheetFragment(state)
                //progressBottomSheetFragment.show(supportFragmentManager, "Progress BottomSheet")
            }
        }

        globalSplitInstallManager.registerListener(globalInstallListener)

        val request = GlobalSplitInstallRequest.newBuilder()
            .addModule(moduleName)
            .build()


        globalSplitInstallManager.startInstall(request)
            .addOnSuccessListener { sessionId ->
            globalSessionId = sessionId
            val currentState=globalSplitInstallManager.getSessionState(sessionId).result.status()

            when(currentState){
                GlobalSplitInstallSessionStatus.DOWNLOADING->{
                    Timber.e("Downloading")
                }

                GlobalSplitInstallSessionStatus.DOWNLOADED->{
                    Timber.e("Downloaded")
                }
            }

        }
        .addOnFailureListener { exception ->
            when((exception as GlobalSplitInstallException).errorCode){
                GlobalSplitInstallErrorCode.NETWORK_ERROR->{
                    Timber.e("Network error downloading feature")
                }

                GlobalSplitInstallErrorCode.INSUFFICIENT_STORAGE ->{
                    Timber.e("Insufficient storage error downloading feature")
                }
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.home_fragment -> {
                navController.navigate(R.id.settings_fragment)
                return true
            }
            R.id.favorites_fragment -> {
                if (globalSplitInstallManager.installedModules.contains("favorites")) {
                    navController.navigate(R.id.favorites_fragment)
                } else installDynamicModule("favorites", R.id.favorites_fragment)

                return true
            }
            R.id.settings_fragment -> {
                navController.navigate(R.id.settings_fragment)
                return true
            }
        }

        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        globalSplitInstallManager.unregisterListener(globalInstallListener)
        _binding = null
    }
}