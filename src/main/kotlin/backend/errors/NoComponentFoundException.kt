package backend.errors

class NoComponentFoundException(component: String) : Exception("No $component found!")