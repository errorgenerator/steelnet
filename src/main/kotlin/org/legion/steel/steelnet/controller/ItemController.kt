package org.legion.steel.steelnet.controller

import org.legion.steel.steelnet.service.item.ItemService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
    @Autowired private var itemService: ItemService
) {

    @GetMapping("/item/**", produces = [MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE])
    public fun getItemByTypeAndName() {
        TODO("Not Implemented yet")
    }
}