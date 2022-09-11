package org.legion.steel.steelnet.service.item

import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.springframework.http.ResponseEntity

abstract class AbstractItemService {

    public abstract fun buildResponse(name: String): ResponseEntity<ItemDTOInterface>
    abstract fun buildListResponse(vararg itemType: String, searchForTypes: Boolean): ResponseEntity<List<ItemDTOInterface>>
    abstract fun buildResponseForAll(): ResponseEntity<List<ItemDTOInterface>>
    abstract fun getItemListByTypeFromDB(itemType: String, foundItems: MutableList<ItemDTOInterface>)
    abstract fun getItemListByXFromDB(key: String, value: String, foundItems: Any?)

}