package com.example.chatapp.activits

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.chatapp.R
import com.example.chatapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this , R.layout.activity_main)

        // Operation work for fragment
        val navHostFragment : NavHostFragment   = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController   : NavController     = navHostFragment.navController

        // Operation work for action bar for page
        var appBarConfiguration = AppBarConfiguration(setOf(R.id.regisrtationFragment ,
            R.id.logInFragment ,
            R.id.profileFragment,
            R.id.usersFragment ,
            R.id.chatFragment))
        setupActionBarWithNavController(navController , appBarConfiguration)

        // operation for hide and show action bar on page
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id){

                R.id.splashFragment     ->supportActionBar!!.hide()
                R.id.usersFragment      ->supportActionBar!!.hide()
                R.id.profileFragment    ->supportActionBar!!.hide()
                R.id.chatFragment       ->supportActionBar!!.hide()

                else -> supportActionBar!!.show()
            }

        }
    }
}