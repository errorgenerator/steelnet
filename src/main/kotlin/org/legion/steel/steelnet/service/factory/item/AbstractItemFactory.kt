package org.legion.steel.steelnet.service.factory.item

import org.legion.steel.steelnet.dto.ItemDTO
import org.legion.steel.steelnet.model.item.AbstractItemModel
import org.springframework.stereotype.Component

@Component
abstract class AbstractItemFactory {
    abstract fun create(foundItem: AbstractItemModel): ItemDTO
}