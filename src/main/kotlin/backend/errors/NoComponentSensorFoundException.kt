package backend.errors

class NoComponentSensorFoundException(component: String) : Exception("No sensor for the $component found!")