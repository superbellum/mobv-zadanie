package sk.stuba.fei.api.mobv.zadanie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import sk.stuba.fei.api.mobv.zadanie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.myNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavView = findViewById(R.id.bottomNavigationView)

        bottomNavView.setupWithNavController(navController)

        appBarConfiguration = getAppbarConfiguration()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun getAppbarConfiguration() = AppBarConfiguration(
        setOf(
            R.id.homeScreen,
            R.id.findPubFormScreen,
            R.id.listPubsScreen,
            R.id.listWebPubsScreen
        )
    )
}