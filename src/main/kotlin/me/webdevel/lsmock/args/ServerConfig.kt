package me.webdevel.lsmock.args

data class ServerConfig(
    val tcpServerPort: Int? = null
)

// dumb but no extra deps
object ServerArgsParser {

    fun parse(args: Array<String>): ServerConfig {
        var port: Int? = null
        var i = 0

        while (i < args.size) {
            when (args[i]) {
                "--tcpServerPort" -> {
                    if (i + 1 >= args.size) {
                        throw RuntimeException("--tcpServerPort requires a port number")
                    }
                    port = parsePort(args[i + 1])
                    break
                }
                else -> {
                    i++
                }
            }
        }

        return ServerConfig(tcpServerPort = port)
    }

    private fun parsePort(portStr: String): Int {
        val parsedPort = portStr.toIntOrNull()
            ?: throw RuntimeException("Invalid port number: $portStr")

        if (parsedPort !in 1..65535) {
            throw RuntimeException("Port must be between 1 and 65535")
        }

        return parsedPort
    }
}