package com.webskitters.assignment.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.webskitters.assignment.R

class HomeActivity : AppCompatActivity() {

    lateinit var navController:NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        toolbar.title=""
        setSupportActionBar(toolbar)

         navController = findNavController(R.id.navHostFragment)
        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.homeFragment, R.id.mapsFragment, R.id.userFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavigationView.setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}