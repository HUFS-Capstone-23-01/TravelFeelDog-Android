package com.example.travelfeeldog.presentation

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.ActivityMainBinding
import com.example.travelfeeldog.presentation.common.BaseActivity
import com.example.travelfeeldog.presentation.common.WindowUtil
import com.example.travelfeeldog.presentation.common.navigation.OnRequestNavigateNotBottomViewListener

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    OnRequestNavigateNotBottomViewListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bnvMain.setupWithNavController(findNavController())
        setNavigationBarVisibility()

    }

    private fun findNavController(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcv_main) as NavHostFragment

        return navHostFragment.navController
    }

    private fun setNavigationBarVisibility() {
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            binding.bnvMain.visibility =
                when (destination.id) {
                    R.id.nav_home, R.id.nav_my_page -> View.VISIBLE
                    else -> View.GONE
                }
        }
    }

    override fun onRequestNavigate(itemId: Int) {
        binding.bnvMain.selectedItemId = itemId
    }

}