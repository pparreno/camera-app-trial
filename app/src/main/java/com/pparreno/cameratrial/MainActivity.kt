package com.pparreno.cameratrial

import android.os.Bundle
import android.view.Menu
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pparreno.cameratrial.databinding.ActivityImageAdjustmentBinding
import com.pparreno.cameratrial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var fab : FloatingActionButton
    lateinit var bottomAppBar : BottomAppBar
    lateinit var navView: BottomNavigationView

    private var isBottomAppBarHidden : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)

        bottomAppBar = binding.bottomAppBar
        setSupportActionBar(binding.bottomAppBar)

        navView= binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_timeline, R.id.navigation_camera, R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        fab = binding.fab
        fab.setOnClickListener(View.OnClickListener {
            if(isBottomAppBarHidden)
            {
                isBottomAppBarHidden = false
                bottomAppBar.performShow()
                navView.visibility = View.VISIBLE
            } else {
                isBottomAppBarHidden = true
                bottomAppBar.performHide()
                navView.visibility = View.GONE
            }

        })
    }
}