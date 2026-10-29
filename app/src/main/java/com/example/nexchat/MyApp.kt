package com.example.nexchat

import android.app.Application
import com.cloudinary.android.MediaManager
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.debug.DebugAppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory

import com.example.nexchat.utils.Constants
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Firebase Initialization
        FirebaseApp.initializeApp(this)

        // Unsigned Cloudinary uploads do not require an API key or API secret in the client.
        try {
            val config: MutableMap<String, String> = HashMap()
            config["cloud_name"] = Constants.CLOUDINARY_CLOUD_NAME
            MediaManager.init(this, config)
        } catch (e: Exception) {
            android.util.Log.e("MyApp", "Cloudinary already initialized or failed: ${e.message}")
        }

        // Initialize Firebase App Check
        val firebaseAppCheck = FirebaseAppCheck.getInstance()
        if (BuildConfig.DEBUG) {
            firebaseAppCheck.installAppCheckProviderFactory(
                DebugAppCheckProviderFactory.getInstance()
            )
            // Debug token will be printed in Logcat for registration
            android.util.Log.d("AppCheck", "Debug token is active. Check Logcat for 'Enter this debug secret' to find your token.")
        } else {
            firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance()
            )
        }
    }
}
