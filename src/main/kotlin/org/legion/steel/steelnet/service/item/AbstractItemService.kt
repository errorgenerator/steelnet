package org.legion.steel.steelnet.service.item

import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.springframework.http.ResponseEntity

abstract class AbstractItemService {

    public abstract fun buildResponse(name: String, token: String): ResponseEntity<ItemDTOInterface>
    abstract fun buildListResponse(vararg itemType: String,token: String, searchForTypes: Boolean): ResponseEntity<List<ItemDTOInterface>>
    abstract fun buildResponseForAll(token: String): ResponseEntity<List<ItemDTOInterface>>
    abstract fun getItemListByTypeFromDB(itemType: String, foundItems: MutableList<ItemDTOInterface>)
    abstract fun getItemListByXFromDB(key: String, value: String, foundItems: MutableList<ItemDTOInterface>)

}