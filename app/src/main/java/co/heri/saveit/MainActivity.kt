package co.heri.saveit

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.navigation.fragment.NavHostFragment
import co.heri.saveit.models.Week


class MainActivity : AppCompatActivity() {



    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController
    lateinit var navBottomView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        navBottomView = findViewById(R.id.bottom_nav_view)



        navHostFragment = supportFragmentManager.findFragmentById(R.id.navhost) as NavHostFragment

        navController = navHostFragment.navController


        NavigationUI.setupWithNavController(navBottomView, navController)

        NavigationUI.setupActionBarWithNavController(this, navController)



    }

    override fun onSupportNavigateUp() = NavigationUI.navigateUp(navController, null)

}
