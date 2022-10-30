package com.example.mobvzadanie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.mobvzadanie.data.Datasource
import com.example.mobvzadanie.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = findNavController(R.id.myNavHostFragment)
        bottomNavView = findViewById(R.id.bottomNavigationView)

        NavigationUI.setupActionBarWithNavController(this, navController)
        bottomNavView.setupWithNavController(navController)

        Datasource.loadPubs(resources.openRawResource(R.raw.pubs))
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || this.onNavigateUp()
    }
}