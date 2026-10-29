package com.example.nexchat.utils

import com.example.nexchat.BuildConfig

object Constants {
    // Public client identifier: configure via local.properties or CI environment.
    // Do not place server-side secrets in BuildConfig or the APK.
    val AGORA_APP_ID: String = BuildConfig.AGORA_APP_ID

    // Cloudinary client-side configuration. Never embed CLOUDINARY_API_SECRET in the app.
    val CLOUDINARY_CLOUD_NAME: String = BuildConfig.CLOUDINARY_CLOUD_NAME
    val CLOUDINARY_UPLOAD_PRESET: String = BuildConfig.CLOUDINARY_UPLOAD_PRESET

    fun formatLastSeen(timestamp: Long): String {
        val sdf = java.text.SimpleDateFormat(
            "hh:mm a",
            java.util.Locale.getDefault()
        )
        val now = System.currentTimeMillis()
        val diff = now - timestamp

        return when {
            diff < 60000 -> "online"
            else -> "last seen today at ${sdf.format(java.util.Date(timestamp))}"
        }
    }
}
