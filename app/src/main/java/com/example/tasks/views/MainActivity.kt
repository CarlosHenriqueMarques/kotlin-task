package com.example.tasks.views

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tasks.R
import com.example.tasks.business.PriorityBusiness
import com.example.tasks.constants.TaskConstants
import com.example.tasks.repository.PriorityCacheConstants
import com.example.tasks.util.SecurityPreferences
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var mSecurityPreferences: SecurityPreferences
    private lateinit var mPriorityBussiness : PriorityBusiness

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_todo,
                R.id.nav_logout
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        mSecurityPreferences = SecurityPreferences(this)
        mPriorityBussiness = PriorityBusiness(this)

        loadPriorityCache()
        setNavigationViewListener()

        startDefaultFragment()
    }

    //Ciclo de vida completo e na ordem
    /*override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }*/

    private fun loadPriorityCache() {
        PriorityCacheConstants.setCache(mPriorityBussiness.getList())
    }

    private fun setNavigationViewListener() {
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        var fragment : Fragment? = null
        val id = menu.itemId
        if(id == R.id.nav_home){
            fragment = TaskListFragment.newInstance()
        }else if (id == R.id.nav_todo){
            fragment = TaskListFragment.newInstance()
        }else if(id == R.id.nav_logout){
            handleLogout()
        }

        val fragmentManager = supportFragmentManager
        fragment?.let { fragmentManager.beginTransaction().replace(R.id.frameContent, it).commit() }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun startDefaultFragment() {
        val fragment : Fragment = TaskListFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.frameContent, fragment).commit()
    }

    private fun handleLogout() {
        mSecurityPreferences.removeStoreString(TaskConstants.KEY.USER_ID)
        mSecurityPreferences.removeStoreString(TaskConstants.KEY.USER_NAME)
        mSecurityPreferences.removeStoreString(TaskConstants.KEY.USER_EMAIL)

        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}
