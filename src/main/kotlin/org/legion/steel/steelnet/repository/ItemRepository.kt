package org.legion.steel.steelnet.repository

import org.legion.steel.steelnet.model.ItemModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository: MongoRepository<ItemModel, String> {

    fun getItemModelsByItemType(itemType: String): MutableList<ItemModel>

    @Query("{'?0': ?1}")
    fun getItemModelByFieldAndValue(field: String, value: String): MutableList<ItemModel>
}