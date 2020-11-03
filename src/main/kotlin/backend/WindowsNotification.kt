package backend

import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

class WindowsNotification(val caption: String, val text: String, val type: TrayIcon.MessageType) {

    fun sendNotification() {
        if(SystemTray.isSupported()) {
            val tray = SystemTray.getSystemTray()
            val img = Toolkit.getDefaultToolkit().createImage("icon.png")
            val icon = TrayIcon(img, "Image")
            icon.isImageAutoSize = true
            tray.add(icon)

            icon.displayMessage(caption, text, type)
        }else {
            println("System tray is not supported!")
        }
    }

}