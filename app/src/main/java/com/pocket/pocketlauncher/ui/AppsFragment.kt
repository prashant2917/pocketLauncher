package com.pocket.pocketlauncher.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.pocket.pocketlauncher.adapter.AppDataAdapter
import com.pocket.pocketlauncher.databinding.FragmentAppsBinding
import com.pocket.pocketlauncher.model.AppData
import com.pocket.pocketlauncher.viewmodel.AppDataViewModel


class AppsFragment : Fragment() {

    private var _binding: FragmentAppsBinding? = null
    private val binding get() = _binding!!
    private lateinit var appDataViewModel: AppDataViewModel
    private lateinit var appDataAdapter: AppDataAdapter


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
                    appDataAdapter = AppDataAdapter(appDataList as List<AppData>)
                    binding.recyclerApps.adapter = appDataAdapter
                }

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}