package org.legion.steel.steelnet.repository

import org.legion.steel.steelnet.model.ItemModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: MongoRepository<ItemModel, Int> {

    fun getItemModelsByItemType(itemType: String): MutableList<ItemModel>
}