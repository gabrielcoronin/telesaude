package com.fiap.telesaude.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.fiap.telesaude.R
import com.fiap.telesaude.databinding.ActivityMainBinding
import com.fiap.telesaude.databinding.DialogLoadingBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).also {
            setSupportActionBar(it.toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)

            navController = Navigation.findNavController(this, R.id.nav_fragment)
            navController.addOnDestinationChangedListener { _, destination, _ ->
                val fragmentsWithoutBottomNavigation = listOf(
                    R.id.mainFragment,
                    R.id.registerFragment,
                    R.id.loginFragment
                )

                when (destination.id) {
                    !in fragmentsWithoutBottomNavigation -> {
                        it.tabNavigation.visibility = View.VISIBLE
                    }
                    else -> {
                        it.tabNavigation.visibility = View.GONE
                    }
                }

                val fragmentsWithoutHomeAsUpButton = listOf(
                    R.id.mainFragment,
                    R.id.homeFragment
                )

                when (destination.id) {
                    !in fragmentsWithoutHomeAsUpButton -> supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    else -> supportActionBar?.setDisplayHomeAsUpEnabled(false)
                }

                invalidateOptionsMenu()
            }
            it.tabNavigation.setupWithNavController(navController)
        }

        auth = Firebase.auth
    }

    override fun onStart() {
        super.onStart()

        if (auth.currentUser != null) {
            navController.navigate(R.id.homeFragment)
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val btnLogout = menu?.findItem(R.id.btn_logout)
        btnLogout?.isVisible = auth.currentUser != null
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.btn_logout -> {
            Firebase.auth.signOut()
            navController.navigate(R.id.mainFragment)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}