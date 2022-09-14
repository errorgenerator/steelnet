package org.legion.steel.steelnet.service.util

import java.util.*

class PathVariableSanitizer {

    companion object {
        public fun sanitizePathVariable(pathVariable: String): String {
            return pathVariable.replace("~", "´").replace("_", " ").replace(".", "`").lowercase(Locale.getDefault())
        }
    }

}