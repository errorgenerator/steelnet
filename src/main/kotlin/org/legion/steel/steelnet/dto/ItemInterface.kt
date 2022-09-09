package org.legion.steel.steelnet.dto

interface ItemDTOInterface {
    fun getName(): String?
    fun setName(name: String)
    fun getNumMats(): Int?
    fun setNumMats(numMats: Int)
    fun getMatsType(): String?
    fun setMatsType(matsType: String)
    fun getStackingValues(): Array<Int>?
    fun setStackingValues(vararg values: Int)
    fun setStackingValues(stackingValues: Array<Int>)
    fun getNumPerCrate(): Int?
    fun setNumPerCrate(numPerCrate: Int)
    fun getItemType(): String?
    fun setItemType(itemType: String)
    fun getEquipWeight(): Int?
    fun setEquipWeight(equipWeight: Int)
    fun getEquipSlot(): Int?
    fun setEquipSlot(equipSlot: Int)
    fun getWeaponClass(): String?
    fun setWeaponClass(weaponClass: String)
    fun getAmmoType(): String?
    fun setAmmoType(ammoType: String)
    fun getVehicleClass(): String?
    fun setVehicleClass(vehicleClass: String)
    fun getNumCrew(): Int?
    fun setNumCrew(numCrew: Int)
    fun getPrimaryArmament(): String?
    fun setPrimaryArmament(primaryArmament: String)
    fun getSecondaryArmament(): Array<String>?
    fun setSecondaryArmament(vararg values: String)
}