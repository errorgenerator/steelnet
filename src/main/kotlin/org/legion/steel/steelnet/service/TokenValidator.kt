package org.legion.steel.steelnet.service

import org.legion.steel.steelnet.service.google.sheets.GoogleTokenSheetService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TokenValidator(
    @Autowired private var googleTokenSheetService: GoogleTokenSheetService
) {

    fun validateToken(unformattedToken: String): Boolean {
        val formattedToken = this.formatToken(unformattedToken)
        return this.checkFormatCorrect(unformattedToken) && this.tokenInSheet(formattedToken)
    }

    private fun checkFormatCorrect(unformattedToken: String): Boolean {
        return unformattedToken.startsWith("Bearer ")
    }

    private fun formatToken(unformattedToken: String): String {
        return unformattedToken.replace("Bearer ", "")
    }

    private fun tokenInSheet(formattedToken: String): Boolean {
        return this.googleTokenSheetService.getTokenList().contains(formattedToken)
    }
}