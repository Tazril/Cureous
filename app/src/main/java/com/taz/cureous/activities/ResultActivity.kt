package com.taz.cureous.activities

import android.graphics.Color
import android.view.MenuItem
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.taz.cureous.R
import com.taz.cureous.helper.ApiClient
import com.taz.cureous.helper.Constant
import com.taz.cureous.helper.Constant.DEF_GENDER
import com.taz.cureous.helper.Constant.DEF_YEAR_OF_BIRTH
import com.taz.cureous.helper.Constant.GENDER_KEY
import com.taz.cureous.helper.Constant.SYMPTOM_LIST_KEY
import com.taz.cureous.helper.Constant.YEAR_OF_BIRTH_KEY
import com.taz.cureous.helper.SharedPrefs
import com.taz.cureous.helper.Urls
import com.taz.cureous.mvp.BaseActivity
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : BaseActivity(R.layout.activity_result) {

    override lateinit var progressBar: ProgressBar
    override var swipeRefreshLayout: SwipeRefreshLayout? = null
    override var toolbar: Toolbar? = null

    val rootDestinations = setOf(R.id.specialisationListView, R.id.diagnosisListView)
    lateinit var navController: NavController
    lateinit var sIdList: ArrayList<Int>
    var gender: String = DEF_GENDER
    var yob: Int = DEF_YEAR_OF_BIRTH

    override fun initActivity() {
        swipeRefreshLayout = swipeRefreshLayoutResult
        progressBar = progressBarResult
        toolbar = toolbarResults as Toolbar
        setSupportActionBar(toolbar)
        navController = findNavController(R.id.fragmentHostResult)

        bottomNavResult.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
        swipeRefreshLayoutResult.setColorSchemeColors(Color.BLUE, Color.GREEN)

        val bundle = intent.extras
        sIdList = bundle!!.getIntegerArrayList(SYMPTOM_LIST_KEY)!!
        gender = bundle.getString(GENDER_KEY)!!
        yob = bundle.getInt(YEAR_OF_BIRTH_KEY, DEF_YEAR_OF_BIRTH)

    }

    override fun onAttachFragment(fragment: Fragment) {
        ApiClient.instantiate(this)
        Urls.TOKEN = SharedPrefs().instantiate(this).getString("token")!!
        super.onAttachFragment(fragment)
    }

    override fun onSupportNavigateUp(): Boolean = navController.navigateUp()
}
