package org.legion.steel.steelnet.service.google.sheets

import org.legion.steel.steelnet.config.GoogleConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.concurrent.thread
import java.time.Duration

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

    public fun checkForUpdatesEveryXHours(hours: Long) {
        thread(
            start = true
        ) {
            while (!Thread.currentThread().isInterrupted) {
                Thread.sleep(Duration.ofHours(hours).toMillis())
                this.tokens = loadTokensFromSheet()
            }

        }
    }

    public fun getTokenList(): List<String> {
        return this.tokens
    }

}