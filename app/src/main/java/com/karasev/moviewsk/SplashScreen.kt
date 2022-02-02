package com.karasev.moviewsk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.airbnb.lottie.LottieAnimationView

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)

        val lottie: LottieAnimationView = findViewById(R.id.splashLottieAnim)
        lottie.animate()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,SignUpActivity::class.java))
            finish()
        },3000)
    }
}