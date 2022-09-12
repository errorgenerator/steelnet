package org.legion.steel.steelnet.service.util

import org.legion.steel.steelnet.dto.ItemDTO
import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.legion.steel.steelnet.model.ItemModel
import org.springframework.stereotype.Service

@Service
class ItemMapperService {

    fun mapItemModelInBulk(dtoList: MutableList<ItemDTOInterface>): MutableList<ItemModel> {
        val returnList = emptyList<ItemModel>().toMutableList()

        dtoList.forEach {
            returnList.add(this.mapItemModel(it))
        }

        return returnList
    }

    fun mapItemModel(input: ItemDTOInterface): ItemModel {
        val itemModel = ItemModel()

        input.getName()?.let { itemModel.setName(it) }
        input.getNumMats()?.let { itemModel.setNumMats(it) }
        input.getMatsType()?.let { itemModel.setMatsType(it) }
        input.getStackingValues()?.let { itemModel.setStackingValues(*it) }
        input.getNumPerCrate()?.let { itemModel.setNumPerCrate(it) }
        input.getItemType()?.let { itemModel.setItemType(it) }
        input.getEquipSlot()?.let { itemModel.setEquipSlot(it) }
        input.getWeaponClass()?.let { itemModel.setWeaponClass(it) }
        input.getAmmoType()?.let { itemModel.setAmmoType(it) }
        input.getVehicleClass()?.let { itemModel.setVehicleClass(it) }
        input.getNumCrew()?.let { itemModel.setNumCrew(it) }
        input.getPrimaryArmament()?.let { itemModel.setPrimaryArmament(it) }
        input.getSecondaryArmament()?.let { itemModel.setSecondaryArmament(*it) }
        input.getDescription()?.let { itemModel.setDescription(it) }

        return itemModel
    }

    fun mapItemDTOInBulk(databaseEntries: MutableList<ItemModel>): MutableList<ItemDTOInterface> {
        val returnList = emptyList<ItemDTOInterface>().toMutableList()

        databaseEntries.forEach {
            returnList.add(this.mapItem(it))
        }


        return returnList
    }

    fun mapItem(itemModel: ItemModel): ItemDTOInterface {
        val returnModel = ItemDTO()

        when (itemModel.getName() != null) {
            true -> {
                itemModel.getName()?.let { returnModel.setName(it) }
            }

            false -> {
                returnModel.setName("UNKNOWN NAME")
            }
        }

        when (itemModel.getNumMats() != null) {
            true -> {
                itemModel.getNumMats()?.let { returnModel.setNumMats(it) }
            }

            false -> {
                returnModel.setNumMats("NAN")
            }
        }

        when (itemModel.getStackingValues() != null) {
            true -> {
                itemModel.getStackingValues()?.let { returnModel.setStackingValues(*it) }
            }

            false -> {
                returnModel.setStackingValues(*emptyArray<String>())
            }
        }

        when (itemModel.getNumPerCrate() != null) {
            true -> {
                itemModel.getNumPerCrate()?.let { returnModel.setNumPerCrate(it) }
            }

            false -> {
                returnModel.setNumPerCrate("NAN")
            }
        }

        when (itemModel.getItemType() != null) {
            true -> {
                itemModel.getItemType()?.let { returnModel.setItemType(it) }
            }

            false -> {
                returnModel.setItemType("UNKNOWN TYPE")
            }
        }

        when (itemModel.getEquipWeight() != null) {
            true -> {
                itemModel.getEquipWeight()?.let { returnModel.setEquipWeight(it) }
            }

            false -> {
                returnModel.setEquipWeight("NAN")
            }
        }

        when (itemModel.getEquipSlot() != null) {
            true -> {
                itemModel.getEquipSlot()?.let { returnModel.setEquipSlot(it) }
            }

            false -> {
                returnModel.setEquipSlot("NAN")
            }
        }

        when (itemModel.getWeaponClass() != null) {
            true -> {
                itemModel.getWeaponClass()?.let { returnModel.setWeaponClass(it) }
            }

            false -> {
                returnModel.setWeaponClass("NAN")
            }
        }

        when (itemModel.getAmmoType() != null) {
            true -> {
                itemModel.getAmmoType()?.let { returnModel.setAmmoType(it) }
            }

            false -> {
                returnModel.setAmmoType("NAN")
            }
        }

        when (itemModel.getPrimaryArmament() != null) {
            true -> {
                itemModel.getPrimaryArmament()?.let { returnModel.setPrimaryArmament(it) }
            }

            false -> {
                returnModel.setPrimaryArmament("NAN")
            }
        }

        when (itemModel.getSecondaryArmament() != null) {
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