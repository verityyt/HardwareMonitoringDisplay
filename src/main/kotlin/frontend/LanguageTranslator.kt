package frontend

object LanguageTranslator {

    var language = "en"

    fun get(text: String): String {
        if(language == "en") {
            if(text == "loading.fonts") {
                return "Registering fonts..."
            }else if(text == "loading.cpu") {
                return "Searching for CPU..."
            }else if(text == "loading.gpu") {
                return "Searching for GPU..."
            }else if(text == "loading.finished") {
                return "Finished!"
            }
        }else if(language == "de") {
            if(text == "loading.fonts") {
                return "Registriere Schriftarten..."
            }else if(text == "loading.cpu") {
                return "Suche nach CPU..."
            }else if(text == "loading.gpu") {
                return "Suche nach GPU..."
            }else if(text == "loading.finished") {
                return "Beendet!"
            }
        }
        return "\"$text\" not found!"
    }

}