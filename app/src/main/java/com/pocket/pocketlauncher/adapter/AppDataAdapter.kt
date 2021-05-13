package com.pocket.pocketlauncher.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pocket.pocketlauncher.databinding.RowAppDataBinding
import com.pocket.pocketlauncher.interfaces.ClickListener
import com.pocket.pocketlauncher.model.AppData

class AppDataAdapter(
    private var appDataList: List<AppData>,
    private var clickListener: ClickListener
) :
    RecyclerView.Adapter<AppDataAdapter.AppDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppDataViewHolder {
        val binding = RowAppDataBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AppDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppDataViewHolder, position: Int) {
        with(holder) {
            this.binding.tvAppName.text = appDataList[position].appName
            appDataList[position].appIcon.let {
                this.binding.ivAppIcon.setImageDrawable(it)
            }
            this.binding.root.setOnClickListener {
                clickListener.onClick(binding.root, position)

            }

            binding.root.setOnLongClickListener { view ->
                clickListener.onLongClick(view, position)
                true
            }


        }
    }


    override fun getItemCount(): Int {
        return appDataList.size
    }

    class AppDataViewHolder(val binding: RowAppDataBinding) :
        RecyclerView.ViewHolder(binding.root)


}



