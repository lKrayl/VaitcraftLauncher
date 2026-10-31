/*
 * Vaitcraft - based on Zalith Launcher 2
 * Copyright (C) 2025 MovTery and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 */

package com.movtery.zalithlauncher.game.bundled

import android.content.Context
import android.net.Uri
import com.movtery.zalithlauncher.game.version.installed.VersionsManager
import com.movtery.zalithlauncher.setting.AllSettings
import com.movtery.zalithlauncher.utils.logging.Logger
import com.movtery.zalithlauncher.viewmodel.ModpackImportOperation
import com.movtery.zalithlauncher.viewmodel.ModpackImportViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException

/**
 * Instala la instancia inicial de Vaitcraft incluida en assets/vaitcraft/modpack.zip.
 *
 * El importador MultiMC existente instala Minecraft y NeoForge. El ZIP incluye metadatos
 * y configuración inicial, no los JAR de mods; estos se podrán añadir después desde el launcher.
 * Si la instalación falla o se cancela, se podrá reintentar.
 */
object BundledModpack {
    private const val TAG = "VaitcraftBundledPack"
    private const val ASSET_PATH = "vaitcraft/modpack.zip"
    private const val VERSION_NAME = "Vaitcrasito-1.0"

    fun installIfNeeded(
        context: Context,
        scope: CoroutineScope,
        viewModel: ModpackImportViewModel
    ) {
        if (AllSettings.bundledModpackDone.getValue()) return

        // No sobrescribir una instancia que el jugador ya haya instalado o personalizado.
        if (VersionsManager.isVersionExists(VERSION_NAME, checkJson = true)) {
            AllSettings.bundledModpackDone.save(true)
            return
        }

        if (viewModel.importOperation != ModpackImportOperation.None) return

        scope.launch {
            val packFile = withContext(Dispatchers.IO) {
                extractBundledPack(context)
            } ?: return@launch

            // Revalidar después de extraer: puede haber empezado otra importación.
            if (AllSettings.bundledModpackDone.getValue() ||
                VersionsManager.isVersionExists(VERSION_NAME, checkJson = true) ||
                viewModel.importOperation != ModpackImportOperation.None
            ) return@launch

            Logger.info(TAG, "Starting first-run import from " + packFile.name)
            viewModel.import(
                context = context,
                uri = Uri.fromFile(packFile),
                localFile = packFile,
                useSuggestedName = true,
                onSuccess = {
                    AllSettings.bundledModpackDone.save(true)
                    Logger.info(TAG, "Initial Vaitcraft instance installed successfully.")
                }
            )
        }
    }

    private fun extractBundledPack(context: Context): File? {
        return try {
            val output = File(context.cacheDir, "vaitcraft/modpack.zip")
            output.parentFile?.mkdirs()
            context.assets.open(ASSET_PATH).use { input ->
                output.outputStream().use { destination -> input.copyTo(destination) }
            }
            output.takeIf { it.isFile && it.length() > 0L }
        } catch (e: IOException) {
            Logger.warning(TAG, "Bundled Vaitcraft modpack asset is unavailable.", e)
            null
        } catch (e: Exception) {
            Logger.error(TAG, "Could not extract the bundled Vaitcraft modpack.", e)
            null
        }
    }
}