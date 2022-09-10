package org.legion.steel.steelnet.service.item

import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.legion.steel.steelnet.service.util.DataCacheService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ItemService(
    @Autowired private var dataCacheService: DataCacheService
): AbstractItemService() {
    override fun buildResponse(name: String): ResponseEntity<ItemDTOInterface> {
        val foundItem = this.dataCacheService.getSheetsData()[name]
        if(foundItem != null) {
            return ResponseEntity.ok(foundItem)
        }
        return ResponseEntity.notFound().build()
    }
}