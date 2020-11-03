package backend

import java.awt.TrayIcon
import java.io.File
import java.util.*
import javax.sound.sampled.AudioSystem

import javax.sound.sampled.AudioInputStream
import kotlin.math.roundToInt

object NotificationManager {

    private var soundPlaying = false

    private val cpuTempBorder = Configuration.get("alarm_cpu").toDouble()
    private val gpuTempBorder = Configuration.get("alarm_gpu").toDouble()

    fun checkCpuTemp(temp: Double) {
        if(cpuTempBorder != 0.0 && temp >= cpuTempBorder) {
            playAlarmSound()
            WindowsNotification(LanguageTranslator.get("alarm.cpu.temp"), "${LanguageTranslator.get("alarm.temp.text")} ${(temp * 10.0).roundToInt() / 10.0}°C", TrayIcon.MessageType.WARNING).sendNotification()
        }
    }

    fun checkGpuTemp(temp: Double) {
        if(gpuTempBorder != 0.0 && temp >= gpuTempBorder) {
            playAlarmSound()
            WindowsNotification(LanguageTranslator.get("alarm.gpu.temp"), "${LanguageTranslator.get("alarm.temp.text")} ${(temp * 10.0).roundToInt() / 10.0}°C", TrayIcon.MessageType.WARNING).sendNotification()
        }
    }

    private fun playAlarmSound() {
        if(Configuration.get("alarm_sound").toBoolean() && !soundPlaying) {
            Thread {
                try {
                    val clip = AudioSystem.getClip()
                    val inputStream: AudioInputStream = AudioSystem.getAudioInputStream(
                        File("files/sounds/alarm.wav")
                    )
                    clip.open(inputStream)
                    clip.start()
                    soundPlaying = true

                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                            soundPlaying = false
                        }

                    }, 9000)
                } catch (e: Exception) {
                    System.err.println(e.message)
                }
            }.start()
        }
    }

}