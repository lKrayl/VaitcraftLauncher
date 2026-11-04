/*
 * Vaitcraft Smart Optimization
 * Copyright (C) 2026 Vaitcraft contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */
package com.movtery.zalithlauncher.ui.screens.content.settings

import android.app.ActivityManager
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.movtery.zalithlauncher.R
import com.movtery.zalithlauncher.setting.AllSettings
import com.movtery.zalithlauncher.ui.base.BaseScreen
import com.movtery.zalithlauncher.ui.screens.NestedNavKey
import com.movtery.zalithlauncher.ui.screens.NormalNavKey
import com.movtery.zalithlauncher.ui.screens.TitledNavKey
import com.movtery.zalithlauncher.setting.unit.floatRange
import com.movtery.zalithlauncher.ui.screens.content.settings.layouts.CardPosition
import com.movtery.zalithlauncher.ui.screens.content.settings.layouts.IntSliderSettingsCard
import com.movtery.zalithlauncher.ui.screens.content.settings.layouts.SettingsCard
import com.movtery.zalithlauncher.utils.platform.getMaxMemoryForSettings

private data class DeviceSnapshot(val processor: String, val abi: String, val totalRamMb: Int)

@Composable
fun SmartOptimizationScreen(
    key: NestedNavKey.Settings,
    settingsScreenKey: TitledNavKey?,
    mainScreenKey: TitledNavKey?
) {
    val context = LocalContext.current
    val device = remember(context) {
        val manager = context.getSystemService(ActivityManager::class.java)
        val info = ActivityManager.MemoryInfo().also { manager?.getMemoryInfo(it) }
        DeviceSnapshot(
            processor = if (Build.VERSION.SDK_INT >= 31) Build.SOC_MODEL.ifBlank { Build.HARDWARE } else Build.HARDWARE,
            abi = Build.SUPPORTED_ABIS.firstOrNull() ?: "unknown",
            totalRamMb = (info.totalMem / (1024L * 1024L)).toInt()
        )
    }
    val maxMemory = remember(context) { getMaxMemoryForSettings(context).coerceAtLeast(512) }

    BaseScreen(
        Triple(key, mainScreenKey, false),
        Triple(NormalNavKey.Settings.SmartOptimization, settingsScreenKey, false)
    ) { _ ->
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(stringResource(R.string.vaitcraft_opt_intro), style = MaterialTheme.typography.bodyMedium)
            SettingsCard(modifier = Modifier.fillMaxWidth(), position = CardPosition.Single) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(stringResource(R.string.vaitcraft_opt_device_title), style = MaterialTheme.typography.titleMedium)
                    Text(stringResource(R.string.vaitcraft_opt_device_soc, device.processor))
                    Text(stringResource(R.string.vaitcraft_opt_device_abi, device.abi))
                    Text(stringResource(R.string.vaitcraft_opt_device_ram, device.totalRamMb))
                }
            }
            Text(stringResource(R.string.vaitcraft_opt_profile_title), style = MaterialTheme.typography.titleMedium)
            SettingsCard(
                modifier = Modifier.fillMaxWidth(),
                position = CardPosition.Single,
                title = stringResource(R.string.vaitcraft_opt_profile_performance),
                summary = stringResource(R.string.vaitcraft_opt_profile_performance_desc),
                onClick = {
                    AllSettings.ramAllocation.save((device.totalRamMb * 0.35f).toInt().coerceIn(512, maxMemory))
                    AllSettings.resolutionRatio.save(70)
                }
            )
            SettingsCard(
                modifier = Modifier.fillMaxWidth(),
                position = CardPosition.Single,
                title = stringResource(R.string.vaitcraft_opt_profile_balanced),
                summary = stringResource(R.string.vaitcraft_opt_profile_balanced_desc),
                onClick = {
                    AllSettings.ramAllocation.save((device.totalRamMb * 0.40f).toInt().coerceIn(512, maxMemory))
                    AllSettings.resolutionRatio.save(85)
                }
            )
            SettingsCard(
                modifier = Modifier.fillMaxWidth(),
                position = CardPosition.Single,
                title = stringResource(R.string.vaitcraft_opt_profile_quality),
                summary = stringResource(R.string.vaitcraft_opt_profile_quality_desc),
                onClick = {
                    AllSettings.ramAllocation.save((device.totalRamMb * 0.45f).toInt().coerceIn(512, maxMemory))
                    AllSettings.resolutionRatio.save(100)
                }
            )
            IntSliderSettingsCard(
                modifier = Modifier.fillMaxWidth(),
                position = CardPosition.Single,
                unit = AllSettings.ramAllocation,
                title = stringResource(R.string.vaitcraft_opt_ram_title),
                valueRange = AllSettings.ramAllocation.floatRange.start..maxMemory.toFloat(),
                suffix = "MB",
                fineTuningControl = true
            )
            IntSliderSettingsCard(
                modifier = Modifier.fillMaxWidth(),
                position = CardPosition.Single,
                unit = AllSettings.resolutionRatio,
                title = stringResource(R.string.vaitcraft_opt_resolution_title),
                summary = stringResource(R.string.vaitcraft_opt_resolution_summary),
                valueRange = AllSettings.resolutionRatio.floatRange,
                suffix = "%",
                fineTuningControl = true
            )
            Text(stringResource(R.string.vaitcraft_opt_ram_title) + ": " +
                (AllSettings.ramAllocation.state ?: 0) + " MB")
            Text(stringResource(R.string.vaitcraft_opt_resolution_title) + ": " +
                AllSettings.resolutionRatio.state + "%")
            Text(stringResource(R.string.vaitcraft_opt_limits_note), style = MaterialTheme.typography.bodySmall)
        }
    }
}
