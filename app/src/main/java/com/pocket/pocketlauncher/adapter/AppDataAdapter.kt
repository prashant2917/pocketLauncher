package com.pocket.pocketlauncher.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pocket.pocketlauncher.databinding.RowAppDataBinding
import com.pocket.pocketlauncher.model.AppData

class AppDataAdapter(private var appDataList: List<AppData>) :
    RecyclerView.Adapter<AppDataAdapter.AppDataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppDataViewHolder {
        val binding = RowAppDataBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return AppDataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppDataViewHolder, position: Int) {
        with(holder) {
            this.binding.tvAppName.text = appDataList[position].appName
            this.binding.tvPackageName.text = appDataList[position].packageName

            appDataList[position].appIcon.let {
                this.binding.ivAppIcon.setImageDrawable(it)
            }



        }
    }

    override fun getItemCount(): Int {
        return appDataList.size
    }

    class AppDataViewHolder(val binding: RowAppDataBinding) :
        RecyclerView.ViewHolder(binding.root)


}



