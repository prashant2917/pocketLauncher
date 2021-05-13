package com.pocket.pocketlauncher.extensions

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.Fragment
import com.pocket.pocketlauncher.model.AppData


fun Fragment.launchApp(appData: AppData) {
    val intent: Intent? =
        context?.packageManager?.getLaunchIntentForPackage(appData.packageName.toString())
    startActivity(intent)
}

fun Fragment.showAppInfo(appData: AppData) {
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri: Uri = Uri.fromParts("package", appData.packageName, null)
    intent.data = uri
    context?.startActivity(intent)
}
