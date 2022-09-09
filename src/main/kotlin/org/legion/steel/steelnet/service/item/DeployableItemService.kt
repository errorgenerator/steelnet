package org.legion.steel.steelnet.service.item

import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.springframework.http.ResponseEntity

class DeployableItemService(): AbstractItemService() {
    override fun buildResponse(vararg args: String): ResponseEntity<ItemDTOInterface> {
        TODO("Not yet implemented")
    }
}