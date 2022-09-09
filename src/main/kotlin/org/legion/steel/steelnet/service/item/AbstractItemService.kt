package org.legion.steel.steelnet.service.item

import org.legion.steel.steelnet.dto.ItemDTO
import org.legion.steel.steelnet.model.item.AbstractItemModel
import org.legion.steel.steelnet.repository.ItemRepository
import org.legion.steel.steelnet.service.factory.item.AbstractItemFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
abstract class AbstractItemService @Autowired constructor(
    private var itemRepository: ItemRepository,
    private var abstractItemFactory: AbstractItemFactory
) {

    fun buildResponse(name: String): ResponseEntity<ItemDTO> {

        val foundItem: AbstractItemModel? = this.itemRepository.getItemModelByName(name)

        if(foundItem != null) {
            val createdDTO: ItemDTO = this.abstractItemFactory.create(foundItem)
            return ResponseEntity.ok(createdDTO)
        }

        return ResponseEntity.notFound().build()
    }
}