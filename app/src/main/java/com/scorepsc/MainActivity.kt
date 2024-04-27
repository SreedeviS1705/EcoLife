package com.scorepsc

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.scorepsc.util.DataStoreManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.scorepsc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener, NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var dataStoreManager: DataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_account,
                R.id.navigation_recent, R.id.navigation_help
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //supportActionBar?.hide()
        val navCntrl: NavController? =
            this.let { it1 -> Navigation.findNavController(it1, R.id.nav_host_fragment_activity_main) }
        navCntrl?.addOnDestinationChangedListener(this)

        binding.navView.setOnItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        findNavController(R.id.nav_host_fragment_activity_main).navigateUp()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.sign_out -> {
                GlobalScope.launch { dataStoreManager.clear() }
                var intent = Intent(this, OnBoardActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                finish()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        Log.d("MainActivityyyyyyyyyy", "onDestinationChanged: ")
        if (R.id.payment_success_screen == destination.id || R.id.payment_failed_screen == destination.id || R.id.quizSuccessFragment == destination.id || R.id.scholarshipStartExamFragment == destination.id) {
            binding?.toolbar?.visibility = View.GONE
        } else {
            binding?.toolbar?.visibility = View.VISIBLE
        }

        if (destination.id == R.id.navigation_home) {
            binding.navView.visibility = View.VISIBLE
        } else {
            binding.navView.visibility = View.GONE
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        Log.d("MainActivityyyyyyyyyy", "onNavigationItemSelected: ")
        when (item.itemId) {
            R.id.navigation_home -> {
                findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_home)
            }
            R.id.navigation_recent -> {
                findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_recent)
            }
            R.id.navigation_account -> {
                findNavController(R.id.nav_host_fragment_activity_main).navigate(R.id.navigation_account)
            }
            else -> {
                Log.d("MainActivityyyyyyyyyy", "onNavigationItemSelected: else case")
            }
        }
        return true
    }

    override fun onBackPressed() {

        Log.d("onBackPressed", "Pressed: ")
        if(this.findNavController(R.id.nav_host_fragment_activity_main)?.currentDestination?.id == R.id.scholarshipStartExamFragment) {
            showAlertDialogButtonClicked(this)
        } else {
            super.onBackPressed()
        }
    }

    private fun showAlertDialogButtonClicked(mContext: Context) {
        val dialog = Dialog(mContext)
        dialog.setContentView(R.layout.scholarship_backclick_dialogue)

        val cancel = dialog.findViewById(R.id.button) as Button
        val confirm = dialog.findViewById(R.id.button2) as Button

        confirm.setOnClickListener {
            super.onBackPressed()
            dialog.hide()
        }

        cancel.setOnClickListener {
            dialog.hide()
        }

        dialog.show()
    }


}