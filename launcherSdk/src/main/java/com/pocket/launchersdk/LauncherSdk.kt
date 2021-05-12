package com.pocket.launchersdk

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo


object LauncherSdk {
    fun getPackageList(context: Context): List<ResolveInfo> {
        val packageManager = context.packageManager

        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        return packageManager.queryIntentActivities(intent, 0)


    }
}