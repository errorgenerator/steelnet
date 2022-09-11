package org.legion.steel.steelnet.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName

@JsonRootName("item")
class ItemDTO : ItemDTOInterface {


    @JsonProperty("name")
    private var name: String? = ""

    @JsonProperty("num_mats")
    private var numMats: String? = ""

    @JsonProperty("mats_type")
    private var matsType: String? = ""

    @JsonProperty("stacking_values")
    private var stackingValues: Array<String>? = emptyArray()

    @JsonProperty("num_per_crate")
    private var numPerCrate: String? = ""

    @JsonProperty("type")
    private var itemType: String? = ""

    @JsonProperty("encumbrance")
    private var equipWeight: String? = ""

    @JsonProperty("equip_slot")
    private var equipSlot: String? = ""

    @JsonProperty("weapon_class")
    private var weaponClass: String? = ""

    @JsonProperty("ammo_type")
    private var ammoType: String? = ""

    @JsonProperty("vehicle_class")
    private var vehicleClass: String? = ""

    @JsonProperty("num_crew")
    private var numCrew: String? = ""

    @JsonProperty("primary_armament")
    private var primaryArmament: String? = ""

    @JsonProperty("secondary_armament")
    private var secondaryArmament: Array<String>? = emptyArray()


    override fun getName(): String? {
        return this.name
    }

    override fun setName(name: String) {
        this.name = name
    }

    override fun getNumMats(): String? {
        return this.numMats
    }

    override fun setNumMats(numMats: String) {
        this.numMats = numMats
    }

    override fun getMatsType(): String? {
        return this.matsType
    }

    override fun setMatsType(matsType: String) {
        this.matsType = matsType
    }

    override fun getStackingValues(): Array<String>? {
        return this.stackingValues
    }

    override fun setStackingValues(vararg values: String) {
        val stackingValuesTmp = arrayOf<String>().toMutableList()
        values.forEach { stackingValuesTmp.add(it) }
        this.stackingValues = stackingValuesTmp.toTypedArray()
    }


    override fun getNumPerCrate(): String? {
        return this.numPerCrate
    }

    override fun setNumPerCrate(numPerCrate: String) {
        this.numPerCrate = numPerCrate
    }

    override fun getItemType(): String? {
        return this.itemType
    }

    override fun setItemType(itemType: String) {
        this.itemType = itemType
    }

    override fun getEquipWeight(): String? {
        return this.equipWeight
    }

    override fun setEquipWeight(equipWeight: String) {
        this.equipWeight = equipWeight
    }

    override fun getEquipSlot(): String? {
        return this.equipSlot
    }

    override fun setEquipSlot(equipSlot: String) {
        this.equipSlot = equipSlot
    }

    override fun getWeaponClass(): String? {
        return this.weaponClass
    }

    override fun setWeaponClass(weaponClass: String) {
        this.weaponClass = weaponClass
    }

    override fun getAmmoType(): String? {
        return this.ammoType
    }

    override fun setAmmoType(ammoType: String) {
        this.ammoType = ammoType
    }

    override fun getVehicleClass(): String? {
        return this.vehicleClass
    }

    override fun setVehicleClass(vehicleClass: String) {
        this.vehicleClass = vehicleClass
    }

    override fun getNumCrew(): String? {
        return this.numCrew
    }

    override fun setNumCrew(numCrew: String) {
        this.numCrew = numCrew
    }

    override fun getPrimaryArmament(): String? {
        return this.primaryArmament
    }

    override fun setPrimaryArmament(primaryArmament: String) {
        this.primaryArmament = primaryArmament
    }

    override fun getSecondaryArmament(): Array<String>? {
        return this.secondaryArmament
    }

    override fun setSecondaryArmament(vararg values: String) {
        val tmpVals = arrayOf<String>().toMutableList()
        values.forEach { tmpVals.add(it) }
        this.secondaryArmament = tmpVals.toTypedArray()
    }

}