package org.legion.steel.steelnet.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "items")
class ItemModel(
    @Id
    private var id: String
) {

    @Field("name")
    private var name: String? = ""

    @Field("num_mats")
    private var numMats: String? = ""

    @Field("mats_type")
    private var matsType: String? = ""

    @Field("stacking_values")
    private var stackingValues: Array<String>? = emptyArray()

    @Field("num_per_crate")
    private var numPerCrate: String? = ""

    @Field("type")
    private var itemType: String? = ""

    @Field("encumbrance")
    private var equipWeight: String? = ""

    @Field("equip_slot")
    private var equipSlot: String? = ""

    @Field("weapon_class")
    private var weaponClass: String? = ""

    @Field("ammo_type")
    private var ammoType: String? = ""

    @Field("vehicle_class")
    private var vehicleClass: String? = ""

    @Field("num_crew")
    private var numCrew: String? = ""

    @Field("primary_armament")
    private var primaryArmament: String? = ""

    @Field("secondary_armament")
    private var secondaryArmament: Array<String>? = emptyArray()

    @Field("description")
    private var description: String? = ""

    fun getName(): String? {
        return this.name
    }

    fun setName(name: String) {
        this.name = name
    }

     fun getNumMats(): String? {
        return this.numMats
    }

     fun setNumMats(numMats: String) {
        this.numMats = numMats
    }

     fun getMatsType(): String? {
        return this.matsType
    }

     fun setMatsType(matsType: String) {
        this.matsType = matsType
    }

     fun getStackingValues(): Array<String>? {
        return this.stackingValues
    }

     fun setStackingValues(vararg values: String) {
        val stackingValuesTmp = arrayOf<String>().toMutableList()
        values.forEach { stackingValuesTmp.add(it.trim()) }
        this.stackingValues = stackingValuesTmp.toTypedArray()
    }


     fun getNumPerCrate(): String? {
        return this.numPerCrate
    }

     fun setNumPerCrate(numPerCrate: String) {
        this.numPerCrate = numPerCrate
    }

     fun getItemType(): String? {
        return this.itemType
    }

     fun setItemType(itemType: String) {
        this.itemType = itemType
    }

    fun getEquipWeight(): String? {
        return this.equipWeight
    }

    fun setEquipWeight(equipWeight: String?) {
        this.equipWeight = equipWeight
    }

    fun getEquipSlot(): String? {
        return this.equipSlot
    }

    fun setEquipSlot(equipSlot: String?) {
        this.equipSlot = equipSlot
    }

    fun getWeaponClass(): String? {
        return this.weaponClass
    }

    fun setWeaponClass(weaponClass: String?) {
        this.weaponClass = weaponClass
    }

    fun getAmmoType(): String? {
        return this.ammoType
    }

    fun setAmmoType(ammoType: String?) {
        this.ammoType = ammoType
    }

    fun getVehicleClass(): String? {
        return this.vehicleClass
    }

    fun setVehicleClass(vehicleClass: String?) {
        this.vehicleClass = vehicleClass
    }

    fun getNumCrew(): String? {
        return this.numCrew
    }

    fun setNumCrew(numCrew: String?) {
        this.numCrew = numCrew
    }

    fun getPrimaryArmament(): String? {
        return this.primaryArmament
    }

    fun setPrimaryArmament(primaryArmament: String?) {
        this.primaryArmament = primaryArmament
    }

    fun getSecondaryArmament(): Array<String>? {
        return this.secondaryArmament
    }

    fun setSecondaryArmament(vararg values: String) {
        val tmpVals = arrayOf<String>().toMutableList()
        values.forEach { tmpVals.add(it.trim()) }
        this.secondaryArmament = tmpVals.toTypedArray()
    }

    fun getDescription(): String? {
        return this.description
    }

    fun setDescription(description: String) {
        this.description = description
    }
}