package org.legion.steel.steelnet.service.util

import org.legion.steel.steelnet.dto.ItemDTO
import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.legion.steel.steelnet.model.ItemModel
import org.springframework.stereotype.Service

@Service
class ItemMapperService {

    fun mapInBulk(databaseEntries: MutableList<ItemModel>): MutableList<ItemDTOInterface> {
        val returnList = emptyList<ItemDTOInterface>().toMutableList()

        databaseEntries.forEach{
            returnList.add(this.mapItem(it))
        }


        return returnList
    }

    public fun mapItem(itemModel: ItemModel): ItemDTOInterface {
        val returnModel = ItemDTO()

        when(itemModel.getName() != null) {
            true -> {
                itemModel.getName()?.let { returnModel.setName(it)}
            }
            false -> {
                returnModel.setName("UNKNOWN NAME")
            }
        }

        when(itemModel.getNumMats() != null) {
            true -> {
                itemModel.getNumMats()?.let { returnModel.setNumMats(it) }
            }
            false -> {
                returnModel.setNumMats("NAN")
            }
        }

        when(itemModel.getStackingValues() != null) {
            true -> {
                itemModel.getStackingValues()?.let { returnModel.setStackingValues(*it) }
            }
            false -> {
                returnModel.setStackingValues(*emptyArray<String>())
            }
        }

        when(itemModel.getNumPerCrate() != null) {
            true -> {
                itemModel.getNumPerCrate()?.let { returnModel.setNumPerCrate(it) }
            }
            false -> {
                returnModel.setNumPerCrate("NAN")
            }
        }

        when(itemModel.getItemType() != null ) {
            true -> {
                itemModel.getItemType()?.let { returnModel.setItemType(it) }
            }
            false -> {
                returnModel.setItemType("UNKNOWN TYPE")
            }
        }

        when(itemModel.getEquipWeight() != null) {
            true -> {
                itemModel.getEquipWeight()?.let { returnModel.setEquipWeight(it) }
            }
            false -> {
                returnModel.setEquipWeight("NAN")
            }
        }

        when(itemModel.getEquipSlot() != null) {
            true -> {
                itemModel.getEquipSlot()?.let { returnModel.setEquipSlot(it) }
            }
            false -> {
                returnModel.setEquipSlot("NAN")
            }
        }

        when(itemModel.getWeaponClass() != null) {
            true -> {
                itemModel.getWeaponClass()?.let { returnModel.setWeaponClass(it) }
            }
            false -> {
                returnModel.setWeaponClass("NAN")
            }
        }

        when(itemModel.getAmmoType() != null) {
            true -> {
                itemModel.getAmmoType()?.let {returnModel.setAmmoType(it) }
            }
            false -> {
                returnModel.setAmmoType("NAN")
            }
        }

        when(itemModel.getPrimaryArmament() != null) {
            true -> {
                itemModel.getPrimaryArmament()?.let { returnModel.setPrimaryArmament(it) }
            }
            false -> {
                returnModel.setPrimaryArmament("NAN")
            }
        }

        when(itemModel.getSecondaryArmament() != null) {
            true -> {
                itemModel.getSecondaryArmament()?.let { returnModel.setSecondaryArmament(*it) }
            }
            false -> {
                returnModel.setSecondaryArmament("NAN")
            }
        }

        return returnModel
    }
}