package com.norm.myquicksettings

import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService

class MyQSTitleService : TileService() {
    var isVpnEnable = false

    override fun onClick() {
        super.onClick()

        isVpnEnable = !isVpnEnable
        qsTile.contentDescription = "vpn_status"
        qsTile.state = if (isVpnEnable) Tile.STATE_ACTIVE else Tile.STATE_INACTIVE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            qsTile.subtitle = when (qsTile.state) {
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
            qsTile.stateDescription = when (qsTile.state) {
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
            qsTile.updateTile()
        }
    }
}