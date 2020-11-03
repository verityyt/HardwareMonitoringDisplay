package backend

import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon

class WindowsNotification(val caption: String, val text: String, val type: TrayIcon.MessageType) {

    fun sendNotification() {
        NotificationManager.trayIcon.displayMessage(caption, text, type)
    }

}