package com.pocket.pocketlauncher.viewmodel

import android.content.Context
import android.content.pm.ResolveInfo
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pocket.launchersdk.LauncherSdk
import com.pocket.pocketlauncher.model.AppData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class AppDataViewModel : ViewModel() {
    private var mutableLiveDataAppDataModel = MutableLiveData<List<AppData?>>()
    fun getPackageList(context: Context?, isAscending: Boolean): MutableLiveData<List<AppData?>> {
        viewModelScope.launch {
            val deferredResult = viewModelScope.async(Dispatchers.Main) {
                context?.let { LauncherSdk.getPackageList(it) }
            }

            mutableLiveDataAppDataModel.value =
                context?.let { setAppData(it, deferredResult.await(),isAscending) }

        }
        return mutableLiveDataAppDataModel

    }

   private fun setAppData(context: Context, resolveInfoList: List<ResolveInfo>?,isAscending: Boolean): List<AppData> {
        val appList = ArrayList<AppData>()
       resolveInfoList?.forEach { resolveInfo ->
           val appData = AppData().apply {
               appIcon = resolveInfo.activityInfo.loadIcon(context.packageManager)
               appName = resolveInfo.loadLabel(context.packageManager).toString()
               packageName = resolveInfo.activityInfo.packageName

           }
           appList.add(appData)
       }
       if(isAscending) {
           appList.sortWith { lhs, rhs ->
               lhs?.appName.toString().compareTo(rhs?.appName.toString())
           }
       }
       return appList
    }
}