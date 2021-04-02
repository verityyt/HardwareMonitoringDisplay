package backend.server

import backend.Configuration
import backend.Logger
import frontend.WindowHandler
import org.json.simple.JSONObject
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket

object Server {

    private lateinit var server: ServerSocket
    private val sockets = mutableListOf<Socket>()

    private var allowed = Configuration.get("allow_sdk").toBoolean()

    fun startup() {
        if (allowed) {
            Logger.log("Starting up socket server...", this.javaClass)

            Thread {
                server = ServerSocket(Configuration.get("sdk_port").toInt())

                while (true) {
                    sockets.add(server.accept())
                }
            }.start()
        }
    }

    fun startSending(interval: Int) {
        if (allowed) {
            Logger.log("Started sending input to all clients!", this.javaClass)

            Thread {
                while (true) {
                    Thread.sleep(interval.toLong())

                    for (socket in sockets) {
                        if (!socket.isClosed) {
                            val writer = PrintWriter(socket.getOutputStream(), true)

                            val json = JSONObject()

                            json["cpu_temp"] = WindowHandler.screen.cpuTemperature ?: ""
                            json["gpu_temp"] = WindowHandler.screen.gpuTemperature ?: ""
                            json["drive1_temp"] = WindowHandler.screen.drive1Temperature ?: ""
                            json["drive2_temp"] = WindowHandler.screen.drive2Temperature ?: ""
                            json["ram_load"] = WindowHandler.screen.ramLoad ?: ""

                            writer.println(json.toJSONString())
                        }
                    }
                }
            }.start()
        }
    }

}