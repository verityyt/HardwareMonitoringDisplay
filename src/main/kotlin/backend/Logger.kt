package backend

import java.io.File

object Logger {

    private var file = File("${System.getProperty("java.io.tmpdir")}\\hmd\\latest-log.txt")
    private var folder = File("${System.getProperty("java.io.tmpdir")}\\hmd")

    private var clear = true

    fun log(text: String, caller: Class<*>) {

        if (!folder.exists()) {
            folder.mkdir()
        }

        if (clear) {
            file.writeText("")

            clear = false
        }

        val output = " [${caller.name}] $text"
        println(output)
        file.writeText("${file.readText()}\n$output")

    }

}