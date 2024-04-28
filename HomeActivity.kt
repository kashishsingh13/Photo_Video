package com.exmple.foodzone.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.exmple.foodzone.CuisineListActivity
import com.exmple.foodzone.Fragments.FavoriteFragment
import com.exmple.foodzone.Fragments.HomeFragment
import com.exmple.foodzone.Fragments.NotificationFragment
import com.exmple.foodzone.Fragments.OrderFragment
import com.exmple.foodzone.Preference.PrefManager
import com.exmple.foodzone.R
import com.exmple.foodzone.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding:ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
       setSupportActionBar(binding.toolBar)
        supportActionBar?.title = ""
        var manager = PrefManager(this)
        var user = manager.getUserCredential()

        if (user != null) {
            Toast.makeText(this, "Welcome , ${user.name}", Toast.LENGTH_SHORT).show()
        }
        binding.toolBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }
        binding.navigationView.getHeaderView(0).findViewById<TextView>(R.id.kashi).setOnClickListener {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finishAffinity()
        }
//        binding.btnLogout.setOnClickListener {
//            manager.updateLoginStatus(false)
//            var intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finishAffinity()
//        }
        addFragment(HomeFragment())
        binding.navigationView.setNavigationItemSelectedListener { menuItem->
            menuItem.isChecked = true
            binding.drawerLayout.close()
            when(menuItem.itemId){
                R.id.nav_home->{
                    addFragment(HomeFragment())
                    true
                }
                R.id.nav_favorite -> {
                    addFragment(FavoriteFragment())
                    true
                }

                R.id.nav_notification -> {
                    addFragment(NotificationFragment())
                    true
                }

                R.id.nav_order -> {
                    addFragment(OrderFragment())
                    true
                }
                R.id.nav_logout -> {
                    manager.updateLoginStatus(false)
                    var intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                    true
                }

                R.id.nav_cuisines->{
                    var intent = Intent(this, CuisineListActivity::class.java)
                    startActivity(intent)
                    true
                }
                else->false
            }
        }
    }
    private fun addFragment(fragment: Fragment) {
        var manager = supportFragmentManager
        var transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}