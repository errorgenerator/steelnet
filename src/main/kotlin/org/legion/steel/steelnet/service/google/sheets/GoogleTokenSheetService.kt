package org.legion.steel.steelnet.service.google.sheets

import org.legion.steel.steelnet.config.GoogleConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GoogleTokenSheetService(
    @Autowired googleConfiguration: GoogleConfiguration
): AbstractGoogleSheetsService(
    googleConfiguration
) {

    private var tokens: List<String> = this.loadTokensFromSheet()

    private fun loadTokensFromSheet(): List<String> {
        val fetchedData = this.fetchGoogleSheetsData(this.googleConfiguration.getTokenId(), "Tokens")
        val tokenList = emptyList<String>().toMutableList()
        if(!fetchedData.isNullOrEmpty()) {
            for(row in fetchedData) {
                tokenList.add(row[0].toString())
            }
        }

        return tokenList.toList()
    }

    public fun getTokenList(): List<String> {
        return this.tokens
    }

}