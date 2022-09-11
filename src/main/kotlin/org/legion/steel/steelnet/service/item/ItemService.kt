package org.legion.steel.steelnet.service.item

import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.legion.steel.steelnet.repository.ItemRepository
import org.legion.steel.steelnet.service.util.DataCacheService
import org.legion.steel.steelnet.service.util.ItemMapperService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@Service
class ItemService(
    @Autowired private var dataCacheService: DataCacheService,
    @Autowired private var itemRepository: ItemRepository,
    @Autowired private var itemMapperService: ItemMapperService
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

        when {
            searchForTypes && itemType.size == 1 -> {
                // ask the cache first.
                this.getItemListByTypeFromCache(itemType[0], foundItems)
                if(foundItems.isEmpty()) {
                    this.getItemListByTypeFromDB(itemType[0], foundItems)
                }
            }
            itemType.size == 2 -> {
                // ask the cache first
                this.getItemListByXFromCache(itemType[0], itemType[1], foundItems = foundItems)
                if(foundItems.isEmpty()) {
                    this.getItemListByXFromDB(itemType[0], itemType[1], foundItems = foundItems)
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

    override fun getItemListByTypeFromDB(itemType: String, foundItems: MutableList<ItemDTOInterface>) {
        val databaseEntries = this.itemRepository.getItemModelsByItemType(itemType)
        foundItems.addAll(this.itemMapperService.mapInBulk(databaseEntries))
    }

    override fun getItemListByXFromDB(key: String, value: String, foundItems: Any?) {
        TODO("Not yet implemented")
    }

    private fun getItemListByTypeFromCache(itemType: String, foundItems: MutableList<ItemDTOInterface>) {

        this.dataCacheService.getSheetsData().forEach {
            if (it.value.getItemType() != null && it.value.getItemType() == itemType) {
                foundItems.add(it.value)
            }
        }
    }

    private fun getItemListByXFromCache(vararg itemType: String, foundItems: MutableList<ItemDTOInterface>) {

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