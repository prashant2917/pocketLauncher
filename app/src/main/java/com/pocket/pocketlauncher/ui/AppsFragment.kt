package com.pocket.pocketlauncher.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pocket.pocketlauncher.R
import com.pocket.pocketlauncher.adapter.AppDataAdapter
import com.pocket.pocketlauncher.databinding.FragmentAppsBinding
import com.pocket.pocketlauncher.extensions.launchApp
import com.pocket.pocketlauncher.extensions.showAppInfo
import com.pocket.pocketlauncher.interfaces.ClickListener
import com.pocket.pocketlauncher.interfaces.MenuItemClickListener
import com.pocket.pocketlauncher.interfaces.PopUpMenuItemClickListener
import com.pocket.pocketlauncher.model.AppData
import com.pocket.pocketlauncher.viewmodel.AppDataViewModel


class AppsFragment : Fragment() {

    private var _binding: FragmentAppsBinding? = null
    private val binding get() = _binding!!
    private lateinit var appDataViewModel: AppDataViewModel
    private lateinit var appDataAdapter: AppDataAdapter
    private lateinit var appList: List<AppData>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAppsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        appDataViewModel = ViewModelProviders.of(this).get(AppDataViewModel::class.java)
        appDataViewModel.getPackageList(context, true).observe(viewLifecycleOwner, { appList ->
            appList?.let { appDataList ->
                if (appList.isEmpty()) {
                    binding.recyclerApps.visibility = View.GONE
                } else {
                    binding.recyclerApps.visibility = View.VISIBLE
                    this.appList = appDataList as List<AppData>
                    appDataAdapter = AppDataAdapter(this.appList, clickListener)
                    binding.recyclerApps.adapter = appDataAdapter


                }

            }
        })
    }

    private val clickListener = object : ClickListener {
        override fun onClick(view: View?, position: Int) {
            val appData = appList[position]
            launchApp(appData)
        }

        override fun onLongClick(view: View?, position: Int) {
            val popupMenu = context?.let { view?.let { it1 -> PopupMenu(it, it1) } }
            popupMenu?.menuInflater?.inflate(R.menu.menu_app_data, popupMenu.menu)
            popupMenu?.show()
            popupMenu?.setOnMenuItemClickListener(
                PopUpMenuItemClickListener(
                    position,
                    menuItemClickListener
                )
            )

        }


    }


    private val menuItemClickListener = object : MenuItemClickListener {
        override fun onMenuItemUninstallClick(position: Int) {
            val appData = appList[position]
        }

        override fun onMenuItemAppInfoClick(position: Int) {
            val appData = appList[position]
            showAppInfo(appData)

        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}