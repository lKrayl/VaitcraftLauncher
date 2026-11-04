/*
 * Vaitcraft Launcher
 * Based on Zalith Launcher 2 (GPL-3.0-or-later).
 */

package com.movtery.zalithlauncher.game.renderer.renderers

import com.movtery.zalithlauncher.game.renderer.RendererInterface
import com.movtery.zalithlauncher.path.PathManager
import java.io.File

/**
 * MobileGlues renderer integration.
 *
 * The renderer keeps its configuration in files/mobileglues/config.json.
 * Existing user settings are deliberately preserved; the launcher only
 * creates an empty configuration when MobileGlues has not been configured yet.
 */
object MobileGluesRenderer : RendererInterface {
    override fun getRendererId(): String = "opengles3_mobileglues"

    override fun getUniqueIdentifier(): String = "acaf8100-bbaf-4d68-b30d-38bf42fcd640"

    override fun getRendererName(): String = "MobileGlues"

    override fun getRendererSummary(): String = "OpenGL ES 3 · MobileGlues"

    override fun getRendererEnv(): Lazy<Map<String, String>> = lazy {
        val mobileGluesDir = File(PathManager.DIR_FILES_PRIVATE, "mobileglues")
        if (!mobileGluesDir.isDirectory && !mobileGluesDir.mkdirs()) {
            throw IllegalStateException(
                "Could not create MobileGlues configuration directory: ${mobileGluesDir.absolutePath}"
            )
        }

        val configFile = File(mobileGluesDir, "config.json")
        if (!configFile.exists() && !configFile.createNewFile()) {
            throw IllegalStateException(
                "Could not create MobileGlues configuration file: ${configFile.absolutePath}"
            )
        }

        mapOf(
            "LIBGL_ES" to "3",
            "LIBGL_EGL" to "libmobileglues.so",
            "MG_COUNT_LAUNCH" to "1",
            "MG_DIR_PATH" to mobileGluesDir.absolutePath
        )
    }

    override fun getDlopenLibrary(): Lazy<List<String>> = lazy { emptyList() }

    override fun getRendererLibrary(): String = "libmobileglues.so"

    override fun getRendererEGL(): String = "libmobileglues.so"
}
