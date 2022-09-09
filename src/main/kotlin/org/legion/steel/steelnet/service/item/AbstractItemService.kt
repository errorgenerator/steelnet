package org.legion.steel.steelnet.service.item

import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity

abstract class AbstractItemService @Autowired constructor(
) {

    public abstract fun buildResponse(vararg args: String): ResponseEntity<ItemDTOInterface>

}