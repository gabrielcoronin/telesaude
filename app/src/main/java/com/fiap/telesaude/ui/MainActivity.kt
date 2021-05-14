package com.fiap.telesaude.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.fiap.telesaude.R
import com.fiap.telesaude.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            setSupportActionBar(it.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            val navController = Navigation.findNavController(this, R.id.nav_fragment)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                val fragmentsWithoutBottomNavigation = listOf(
                    R.id.mainFragment,
                    R.id.registerFragment,
                    R.id.loginFragment
                )

                when (destination.id) {
                    !in fragmentsWithoutBottomNavigation -> it.tabNavigation.visibility = View.VISIBLE
                    else -> it.tabNavigation.visibility = View.GONE
                }

                val fragmentsWithoutHomeAsUpButton = listOf(
                    R.id.mainFragment,
                    R.id.homeFragment
                )

                when (destination.id) {
                    !in fragmentsWithoutHomeAsUpButton -> supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    else -> supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }
            }
            it.tabNavigation.setupWithNavController(navController)
        }
    }
}