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
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class ItemController(
    @Autowired private var itemService: ItemService
) {

    @Operation(
        summary = "Returns an Item by Name"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "The Item has been found",
                content = [
                    Content(
                        mediaType = "application/json",
                        schema = Schema(
                            implementation = ItemDTO::class,
                            title = "Item"
                        )
                    ),
                    Content(
                        mediaType = "application/xml",
                        schema = Schema(
                            implementation = ItemDTO::class,
                            title = "Item"
                        )
                    )
                ]
            ),
            ApiResponse(
                responseCode = "401",
                description = "The Transmitted AuthorizationToken was not correct"
            ),
            ApiResponse(
                responseCode = "404",
                description = "The item could not be found"
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error"
            )
        ]
    )
    @GetMapping(
        "/api/v1/item/{name}",
        produces = [MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE]
    )
    @Cacheable(cacheNames = [CacheConfiguration.RESPONSE_CACHE])
    fun getItemByName(
        @PathVariable name: String,
        @RequestHeader("Authorization") authHeader: String
    ): ResponseEntity<ItemDTOInterface> {
        return this.itemService.buildResponse(PathVariableSanitizer.sanitizePathVariable(name), authHeader)
    }

    @Operation(
        summary = "Return a List of Items matching the specified type"
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "At least one entry for the specified type could be found",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(
                        implementation = ItemDTO::class,
                        title = "List of Items"
                    )
                ),
                Content(
                    mediaType = "application/xml",
                    schema = Schema(
                        implementation = ItemDTO::class,
                        title = "List of Items"
                    )
                )
            ]
        ),
        ApiResponse(
            responseCode = "401",
            description = "The Transmitted AuthorizationToken was not correct"
        ),
        ApiResponse(
            responseCode = "404",
            description = "No Entries for the specified MediaType could be found"
        ),
        ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
        )
    )
    @Cacheable(cacheNames = [CacheConfiguration.RESPONSE_CACHE])
    @GetMapping(
        "/api/v1/item/{type}",
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun getItemListByType(
        @PathVariable type: String,
        @RequestHeader("Authorization") authHeader: String
    ): ResponseEntity<List<ItemDTOInterface>> {
        return this.itemService.buildListResponse(
            PathVariableSanitizer.sanitizePathVariable(type),
            token = authHeader,
            searchForTypes = true
        )
    }

    @Operation(
        summary = "Retrieves a list of items where key == value"
    )
    @ApiResponses(
        ApiResponse(),
        ApiResponse(),
        ApiResponse(
            responseCode = "401",
            description = "The Transmitted AuthorizationToken was not correct"
        ),
        ApiResponse()
    )
    @Cacheable(cacheNames = [CacheConfiguration.RESPONSE_CACHE])
    @GetMapping(
        "/api/v1/item/{key}/{value}",
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun getItemListByOther(
        @PathVariable key: String,
        @PathVariable value: String,
        @RequestHeader("Authorization") authHeader: String
    ): ResponseEntity<List<ItemDTOInterface>> {
        val sanKey = PathVariableSanitizer.sanitizePathVariable(key)
        val sanVal = PathVariableSanitizer.sanitizePathVariable(value)
        return this.itemService.buildListResponse(sanVal, sanKey, token = authHeader, searchForTypes = false)
    }

    @Operation(
        summary = "Retrieves all Items at once"
    )
    @ApiResponses(
        ApiResponse(
            responseCode = "200",
            description = "A list of Items is contained within the ResponseBody",
            content = [
                Content(
                    mediaType = "application/json",
                    schema = Schema(
                        implementation = ItemDTO::class,
                        title = "A List of Items"
                    )
                )
            ]
        ),
        ApiResponse(
            responseCode = "401",
            description = "The Transmitted AuthorizationToken was not correct"
        ),
        ApiResponse(
            responseCode = "500",
            description = "Internal Server Error"
        )
    )
    @Cacheable(cacheNames = [CacheConfiguration.RESPONSE_CACHE])
    @GetMapping(
        "/api/v1/item/all",
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE]
    )
    fun getAllItemsAtOnce(@RequestHeader("Authorization") authHeader: String): ResponseEntity<List<ItemDTOInterface>> {
        return this.itemService.buildResponseForAll(authHeader)
    }
}