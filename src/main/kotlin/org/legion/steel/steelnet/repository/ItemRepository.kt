package org.legion.steel.steelnet.repository

import org.legion.steel.steelnet.model.item.AbstractItemModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : MongoRepository<AbstractItemModel?, Int> {

    fun getItemModelByName(name: String): AbstractItemModel?

    fun getItemModelByID(id: Int): AbstractItemModel?

    fun getItemModelByMatAmount(amount: Int): Iterable<AbstractItemModel>?
}