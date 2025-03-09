package me.webdevel.lsmock.args

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ServerConfigTest {

    @Test
    fun `parse empty args returns config with no port`() {
        val config = ServerArgsParser.parse(arrayOf())
        assertNull(config.tcpServerPort)
    }

    @Test
    fun `parse valid port returns config with port`() {
        val config = ServerArgsParser.parse(arrayOf("--tcpServerPort", "8080"))
        assertEquals(8080, config.tcpServerPort)
    }

    @Test
    fun `parse invalid port number throws exception`() {
        val exception = assertThrows<RuntimeException> {
            ServerArgsParser.parse(arrayOf("--tcpServerPort", "invalid"))
        }
        assertEquals("Invalid port number: invalid", exception.message)
    }

    @Test
    fun `parse port out of range throws exception`() {
        val exception = assertThrows<RuntimeException> {
            ServerArgsParser.parse(arrayOf("--tcpServerPort", "70000"))
        }
        assertEquals("Port must be between 1 and 65535", exception.message)
    }

    @Test
    fun `parse missing port value throws exception`() {
        val exception = assertThrows<RuntimeException> {
            ServerArgsParser.parse(arrayOf("--tcpServerPort"))
        }
        assertEquals("--tcpServerPort requires a port number", exception.message)
    }

    @Test
    fun `several args`() {
        val config = ServerArgsParser.parse(arrayOf("--unknown", "--tcpServerPort", "8080", "--unknown2"))
        assertEquals(8080, config.tcpServerPort)
    }
}