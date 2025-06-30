package com.gundogar.e_commerce_demo

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.Insets
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.gundogar.e_commerce_demo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.findNavController()
       // WindowCompat.setDecorFitsSystemWindows(window, true)


        //setupActionBarWithNavController(navController)

        NavigationUI.setupWithNavController(binding.bottomNavView, navController)

        setBottomNavVisibilityForEachFragment(navController)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right,0)
            insets
        }

//        ViewCompat.setOnApplyWindowInsetsListener(binding.bottomNavView) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            // Sadece alt padding'i sistem navigation bar'ı için ayarla
//            v.setPadding(0, 0, 0, systemBars.bottom)
//            insets
//        }

//        ViewCompat.setOnApplyWindowInsetsListener(
//            getWindow().getDecorView(),
//            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
//                val systemInsets: Insets = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
//                v!!.setPadding(
//                    v.getPaddingLeft(),
//                    v.getPaddingTop(),
//                    v.getPaddingRight(),
//                    v.getPaddingBottom() - systemInsets.bottom
//                ) // subtract the insets from the bottom padding
//                insets
//            })

    }
    override fun onSupportNavigateUp(): Boolean = super.onSupportNavigateUp() || navController.navigateUp()

    private fun setBottomNavVisibilityForEachFragment(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.detailFragment -> {
                    binding.bottomNavView.gone()
                }

                else -> {
                    binding.bottomNavView.show()
                }
            }
        }
    }
}