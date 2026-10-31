/*
 * Zalith Launcher 2
 * Copyright (C) 2025 MovTery <movtery228@qq.com> and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/gpl-3.0.txt>.
 */

package com.movtery.zalithlauncher.utils.file

import kotlinx.coroutines.runBlocking
import org.jackhuang.hmcl.util.DigestUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Files
import kotlin.io.path.createTempDirectory

class FileTest {

    @Test
    fun testCalculateFileSha1() = runBlocking {
        val directory = createTempDirectory("file-sha1-test")
        val file = directory.resolve("sample.txt")
        try {
            Files.write(file, "abc".toByteArray(Charsets.UTF_8))

            val actual = calculateFileSha1(file.toFile())
            val expected = "a9993e364706816aba3e25717850c26c9cd0d89d"
            val reference = DigestUtils.digestToString("SHA-1", file)

            assertEquals(expected, actual)
            assertEquals(reference, actual)
        } finally {
            file.toFile().delete()
            directory.toFile().delete()
        }
    }
}
