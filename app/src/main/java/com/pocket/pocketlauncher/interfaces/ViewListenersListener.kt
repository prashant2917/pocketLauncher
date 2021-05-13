package com.pocket.pocketlauncher.interfaces

import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.PopupMenu
import com.pocket.pocketlauncher.R

interface ClickListener {
    fun onClick(view: View?, position: Int)
    fun onLongClick(view: View?, position: Int)
}

interface MenuItemClickListener {
    fun onMenuItemUninstallClick(position: Int)
    fun onMenuItemAppInfoClick(position: Int)
}

class PopUpMenuItemClickListener(
    private val position: Int,
    private val menuItemClickListener: MenuItemClickListener
) : PopupMenu.OnMenuItemClickListener {
    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_app_info -> {
                menuItemClickListener.onMenuItemAppInfoClick(position)
                true
            }
            R.id.menu_uninstall -> {
                menuItemClickListener.onMenuItemUninstallClick(position)
                true
            }
            else -> {
                false
            }
        }
    }


}