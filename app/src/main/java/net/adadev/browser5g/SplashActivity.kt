package net.adadev.browser5g

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import android.preference.PreferenceManager
import java.util.*

class SplashActivity : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd
    private var adRespons = true
    private var runable: Runnable? = null
    var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MobileAds.initialize(this)

        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val lastopen = Date().day //prefs.getInt("lastopen", 0)

        val editor = prefs.edit()
        editor.putInt("lastopen", Date().day)
        editor.apply()

        if (lastopen == Date().day){
            val i = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(i)
            return
        }

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                if(!adRespons)
                    return

                handler.removeCallbacks(runable)
                handler.post(runable)
                Log.i("Ads", "onAdLoaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                if(!adRespons)
                    return

                handler.removeCallbacks(runable)
                handler.post(runable)
                Log.i("Ads", "onAdFailedToLoad")
            }

            override fun onAdOpened() {
                Log.i("Ads", "onAdOpened")
            }

            override fun onAdLeftApplication() {
                Log.i("Ads", "onAdLeftApplication")
            }

            override fun onAdClosed() {
                Log.i("Ads", "onAdClosed")
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
            }
        }

        runable = Runnable {
            adRespons = false
            if (mInterstitialAd.isLoaded) {
                Log.i("Ads", "onAdSHOOOW")
                mInterstitialAd.show()
            } else {
                Log.d("Ads", "The interstitial wasn't loaded yet.")
                val i = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(i)
            }
        }

        startDelay(4000)
    }

    fun startDelay(time: Long){
        handler.removeCallbacks(runable)
        handler.postDelayed(runable, time)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}
