package org.legion.steel.steelnet.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.legion.steel.steelnet.config.CacheConfiguration
import org.legion.steel.steelnet.dto.ItemDTO
import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.legion.steel.steelnet.service.item.ItemService
import org.legion.steel.steelnet.service.util.PathVariableSanitizer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
    @Autowired private var itemService: ItemService
) {

    @Operation(
        summary = "Returns an Item by Name"
    )
    @ApiResponses(value = [
        ApiResponse(
            responseCode = "200",
            description = "The Item has been found",
            content = [
                Content(
                    mediaType = "application/xml application/json text/html",
                    schema = Schema(
                        implementation = ItemDTO::class,
                        title = "Item"
                    )
                )
            ]
        ),
        ApiResponse(
            responseCode = "404",
            description = "The item could not be found"
        ),
        ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
        )
    ])
    @GetMapping(
        "/item/{name}",
        produces = [MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE]
    )
    @Cacheable(cacheNames = [CacheConfiguration.RESPONSE_CACHE])
    fun getItemByName(@PathVariable name: String): ResponseEntity<ItemDTOInterface> {
        return this.itemService.buildResponse(PathVariableSanitizer.sanitizePathVariable(name))
    }
}