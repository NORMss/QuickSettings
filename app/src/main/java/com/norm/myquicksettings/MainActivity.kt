package com.norm.myquicksettings

import android.app.StatusBarManager
import android.content.ComponentName
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.norm.myquicksettings.ui.theme.MyQuickSettingsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyQuickSettingsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(
                            onClick = {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    val statusBarManager = applicationContext.getSystemService(
                                        StatusBarManager::class.java
                                    )
                                    statusBarManager.requestAddTileService(
                                        ComponentName(
                                            "com.norm.myquicksettings",
                                            "com.norm.myquicksettings.MyQSTitleService",
                                        ),
                                        "VPN Switcher",
                                        Icon.createWithResource(
                                            applicationContext,
                                            R.drawable.baseline_vpn_key_24
                                        ),
                                        {},
                                        {}
                                    )
                                }
                            }
                        ) {
                            Text(
                                text = "Add Tile Service"
                            )
                        }
                    }
                }
            }
        }
    }
}