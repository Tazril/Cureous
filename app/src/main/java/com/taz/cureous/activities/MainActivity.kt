package com.taz.cureous.activities

import android.graphics.Color
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.taz.cureous.R
import com.taz.cureous.helper.ApiClient
import com.taz.cureous.helper.SharedPrefs
import com.taz.cureous.helper.Urls
import com.taz.cureous.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(R.layout.activity_main) {

    override lateinit var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null

    lateinit var navController: NavController
    lateinit var configforTopLevelDest: AppBarConfiguration
    val rootDestinations = setOf(R.id.symptomListView, R.id.issueListView)

    override fun initActivity() {
        swipeRefreshLayout = swipeRefreshLayoutMain
        progressBar = progressBarMain
        toolbar = toolbarMain as Toolbar
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.fragmentHostMain)
        configforTopLevelDest = AppBarConfiguration(rootDestinations, drawerLayoutMain)
        primaryNavigationView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, configforTopLevelDest)
        swipeRefreshLayoutMain.setColorSchemeColors(Color.BLUE, Color.GREEN)
        drawerLayoutMain.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    }

    override fun onAttachFragment(fragment: Fragment) {
        ApiClient.instantiate(this)
        Urls.TOKEN = SharedPrefs().instantiate(this).getString("token")!!
        super.onAttachFragment(fragment)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp(configforTopLevelDest)

}

