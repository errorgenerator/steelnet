package org.legion.steel.steelnet.service.util

import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.legion.steel.steelnet.service.google.sheets.GoogleSheetsResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DataCacheService(
    @Autowired private var googleSheetsResolver: GoogleSheetsResolver
) {
    public fun getSheetsData(): HashMap<String?, ItemDTOInterface> {
        return this.googleSheetsResolver.getStoredSheetsData()
    }

    public fun getPossibleKeys(): List<String> {
        return this.googleSheetsResolver.getPossibleKeys()
    }
}