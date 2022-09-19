package com.example.ecommerce.view

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import coil.load
import com.example.ecommerce.databinding.ActivitySplashBinding
import com.example.ecommerce.view.category.CategoryActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        splashBinding.splashImage
            .load("https://www.semessta.com/skin/frontend/smartwave/porto/images/logo.png")


        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}