package com.kevin.uasmovieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.tabs.TabLayout.Tab
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.kevin.uasmovieapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewPagerAdapter: ViewPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)

        val email = intent.getStringExtra("email")

        binding.tvEmail.text = "Hello "+email+" !"

        binding.btnSignoutGoogle.setOnClickListener {
            auth.signOut()
            googleSignInClient.signOut()
            startActivity(Intent(this, AuthGoogleActivity::class.java))
        }

        viewPagerAdapter = ViewPageAdapter(supportFragmentManager,lifecycle)

        with(binding){
            viewPager.adapter = viewPagerAdapter

            TabLayoutMediator(tabLayout,viewPager){tab,position ->
                when(position){
                    0 -> tab.text = "Movie"
                    1 -> tab.text = "Series"
                }
            }.attach()
        }

    }
}