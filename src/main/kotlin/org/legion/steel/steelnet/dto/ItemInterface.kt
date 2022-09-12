package org.legion.steel.steelnet.dto

interface ItemDTOInterface {
    fun getName(): String?
    fun setName(name: String)
    fun getNumMats(): String?
    fun setNumMats(numMats: String)
    fun getMatsType(): String?
    fun setMatsType(matsType: String)
    fun getStackingValues(): Array<String>?
    fun setStackingValues(vararg values: String)
    fun getNumPerCrate(): String?
    fun setNumPerCrate(numPerCrate: String)
    fun getItemType(): String?
    fun setItemType(itemType: String)
    fun getEquipWeight(): String?
    fun setEquipWeight(equipWeight: String)
    fun getEquipSlot(): String?
    fun setEquipSlot(equipSlot: String)
    fun getWeaponClass(): String?
    fun setWeaponClass(weaponClass: String)
    fun getAmmoType(): String?
    fun setAmmoType(ammoType: String)
    fun getVehicleClass(): String?
    fun setVehicleClass(vehicleClass: String)
    fun getNumCrew(): String?
    fun setNumCrew(numCrew: String)
    fun getPrimaryArmament(): String?
    fun setPrimaryArmament(primaryArmament: String)
    fun getSecondaryArmament(): Array<String>?
    fun setSecondaryArmament(vararg values: String)

    fun getDescription(): String?

    fun setDescription(description: String)
}