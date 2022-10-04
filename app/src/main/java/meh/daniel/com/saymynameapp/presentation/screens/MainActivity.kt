package meh.daniel.com.saymynameapp.presentation.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import meh.daniel.com.saymynameapp.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main){

    private var navController: NavController? = null
    private val topLevelDestinations = setOf(getCharacterListDestination(), getCharacterSearchDestination())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolBar))
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController
        val appBarConfig = AppBarConfiguration(setOf(
            getCharacterListDestination(),
            getCharacterSearchDestination(),
        ))
        findViewById<BottomNavigationView>(R.id.mainBottomNavigation).setupWithNavController(navController!!)
        setupActionBarWithNavController(navController!!, appBarConfig)
        supportActionBar?.setDisplayShowCustomEnabled(true)
    }

    override fun onBackPressed() {
        if (isStartDestination(navController?.currentDestination)) {
            super.onBackPressed()
        } else {
            navController?.popBackStack()
        }
    }

    override fun onSupportNavigateUp(): Boolean = (navController?.navigateUp() ?: false) || super.onSupportNavigateUp()

    private fun isStartDestination(destination: NavDestination?): Boolean {
        if (destination == null) return false
        val graph = destination.parent ?: return false
        val startDestinations = topLevelDestinations + graph.startDestinationId
        return startDestinations.contains(destination.id)
    }

    private fun getCharacterListDestination(): Int = R.id.characterListFragment

    private fun getCharacterSearchDestination(): Int = R.id.characterSearch
}