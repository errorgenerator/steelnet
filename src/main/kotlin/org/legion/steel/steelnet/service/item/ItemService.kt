package org.legion.steel.steelnet.service.item

import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.legion.steel.steelnet.service.util.DataCacheService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@Service
class ItemService(
    @Autowired private var dataCacheService: DataCacheService
) : AbstractItemService() {
    override fun buildResponse(name: String): ResponseEntity<ItemDTOInterface> {
        val foundItem = this.dataCacheService.getSheetsData()[name]
        if (foundItem != null) {
            return ResponseEntity.ok(foundItem)
        }
        return ResponseEntity.notFound().build()
    }

    override fun buildListResponse(
        vararg itemType: String,
        searchForTypes: Boolean
    ): ResponseEntity<List<ItemDTOInterface>> {

        val foundItems = emptyList<ItemDTOInterface>().toMutableList()

        if (searchForTypes && itemType.size == 1) {
            this.dataCacheService.getSheetsData().forEach {
                if (it.value.getItemType() != null && it.value.getItemType() == itemType[0]) {
                    foundItems.add(it.value)
                }
            }
        } else {
            if (itemType.size == 2) {
                this.dataCacheService.getSheetsData().forEach { it ->
                    val key = itemType[0].lowercase(Locale.getDefault())
                    val value = itemType[1].lowercase(Locale.getDefault())
                    val entryFields = it.value.javaClass.kotlin.memberProperties
                    entryFields.forEach { ot ->
                        // our fields are all private
                        if (ot.visibility == KVisibility.PRIVATE) {
                            ot.isAccessible = true
                            val nameOfField = ot.name
                            val internalVal = ot.getter.call().toString()
                            ot.isAccessible = false
                            if (key == nameOfField.lowercase(Locale.getDefault()) && value == internalVal.lowercase(Locale.getDefault())) {
                                foundItems.add(it.value)
                            }
                        }
                    }

                }
            }
        }

        if (foundItems.isEmpty()) {
            return ResponseEntity.notFound().build()
        }

        return ResponseEntity.ok(foundItems)
    }

    override fun buildResponseForAll(): ResponseEntity<List<ItemDTOInterface>> {
        return ResponseEntity.ok(this.dataCacheService.getSheetsData().values.toList())
    }
}