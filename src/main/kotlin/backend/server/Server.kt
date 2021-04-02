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

    fun startup() {
        Thread {
            Logger.log("Starting up socket server...", this.javaClass)

            server = ServerSocket(6969)

            while (true) {
                sockets.add(server.accept())
            }
        }.start()
    }

    fun startSending(interval: Int) {
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