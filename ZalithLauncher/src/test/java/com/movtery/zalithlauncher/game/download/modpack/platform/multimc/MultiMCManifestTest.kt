package com.movtery.zalithlauncher.game.download.modpack.platform.multimc

import com.google.gson.Gson
import com.movtery.zalithlauncher.game.addons.modloader.ModLoader
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class MultiMCManifestTest {
    @Test
    fun parsesVaitcrasitoMinecraftAndNeoForgeVersions() {
        val json = """
            {
              "formatVersion": 1,
              "components": [
                {
                  "important": true,
                  "dependencyOnly": false,
                  "uid": "net.minecraft",
                  "version": "26.2"
                },
                {
                  "important": false,
                  "dependencyOnly": false,
                  "uid": "net.neoforged",
                  "version": "26.2.0.93"
                }
              ]
            }
        """.trimIndent()

        val manifest = Gson().fromJson(json, MultiMCManifest::class.java)
        assertEquals("26.2", manifest.getMinecraftVersion())

        val neoForgeComponent = manifest.components.single { it.uid == UID_NEOFORGE }
        val loader = with(manifest) { neoForgeComponent.retrieveLoader() }
        assertNotNull(loader)
        assertEquals(ModLoader.NEOFORGE, loader!!.first)
        assertEquals("26.2.0.93", loader.second)
    }
}