package com.norm.myquicksettings

import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.widget.Toast

class MyQSTitleService : TileService() {
    var isVpnEnable = false

    override fun onTileAdded() {
        super.onTileAdded()
        super.onStartListening()
        qsTile?.apply {
            state = if (isVpnEnable) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                subtitle = "Tile added"
            } else {
                Toast.makeText(
                    applicationContext,
                    "`requestAddTileService` can only be called in Android 13/Tiramisu.",
                    Toast.LENGTH_SHORT,
                ).show()
            }
            updateTile()
        }
    }
    override fun onStartListening() {

    }

    override fun onClick() {
        super.onClick()

        isVpnEnable = !isVpnEnable
        qsTile?.apply {
            contentDescription = "vpn_status"
            state = if (isVpnEnable) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                subtitle = when (state) {
                    Tile.STATE_ACTIVE -> {
                        "On"
                    }

                    Tile.STATE_INACTIVE -> {
                        "Off"
                    }

                    Tile.STATE_UNAVAILABLE -> {
                        "Unavailable"
                    }

                    else -> {
                        "Unknown state"
                    }
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                stateDescription = when (state) {
                    Tile.STATE_ACTIVE -> {
                        "On"
                    }

                    Tile.STATE_INACTIVE -> {
                        "Off"
                    }

                    Tile.STATE_UNAVAILABLE -> {
                        "Unavailable"
                    }

                    else -> {
                        "Unknown state"
                    }
                }
                updateTile()
            }
        }

    }
}