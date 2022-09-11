package org.legion.steel.steelnet.service.google.sheets

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import org.apache.commons.io.IOUtils
import org.legion.steel.steelnet.config.GoogleConfiguration
import org.legion.steel.steelnet.dto.ItemDTO
import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.util.*
import kotlin.concurrent.thread


@Service
class GoogleSheetsResolver(
    @Autowired private var googleConfiguration: GoogleConfiguration
) {

    private val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    private val jsonFactory = GsonFactory.getDefaultInstance()
    private val scopes = Collections.singletonList(SheetsScopes.SPREADSHEETS)

    private lateinit var storedSheetsData: HashMap<String?, ItemDTOInterface>
    private lateinit var possibleKeys: List<String>

    private fun getCredentials(): Credential {
        val credentialsString = File(this.googleConfiguration.getCredentialsFilePath()).inputStream().reader().use { it.readText() }
        val readCredentials = IOUtils.toInputStream(credentialsString, StandardCharsets.UTF_8)

        return GoogleCredential.fromStream(readCredentials).createScoped(scopes)

    }

    private fun fetchGoogleSheetsData(): List<List<Any>>? {
        val service = Sheets.Builder(this.httpTransport, this.jsonFactory, this.getCredentials())
            .setApplicationName(this.googleConfiguration.getApplicationName())
            .build()
        val response = service.spreadsheets().values().get(this.googleConfiguration.getSpreadSheetId(), "Items")
            .execute()
        return response.getValues()

    }


    private fun sortGoogleSheetsData(): MutableMap<String?, ItemDTOInterface> {

        val sortedData = HashMap<String?, ItemDTOInterface>().toMutableMap()

        val fetchedData = this.fetchGoogleSheetsData()

        if (!fetchedData.isNullOrEmpty()) {
            val titleRow = emptyList<String>().toMutableList()

            for(cell in fetchedData[0]) {
                titleRow.add(cell.toString())
            }

            this.possibleKeys = titleRow.toList()

            for(row in fetchedData) {
                if(row[0].toString() == "Name") {
                    continue
                }

                val itemDTO = ItemDTO()

                for(index in row.indices) {
                    val mpfVals = emptyList<String>().toMutableList()
                    when {
                        titleRow[index] == "Name" -> {
                            itemDTO.setName(row[index].toString())
                        }
                        titleRow[index] == "Type" -> {
                            itemDTO.setItemType(row[index].toString())
                        }
                        titleRow[index] == "Base Required Materials" -> {
                            itemDTO.setNumMats(row[index].toString())
                        }
                        titleRow[index] == "Mat Type" -> {
                            itemDTO.setNumMats(row[index].toString())
                        }
                        titleRow[index] == "Num Per Crate" -> {
                            itemDTO.setNumPerCrate(row[index].toString())
                        }
                        titleRow[index] == "Encumbrance" -> {
                            itemDTO.setEquipWeight(row[index].toString())
                        }
                        titleRow[index] == "Equip Slot" -> {
                            itemDTO.setEquipSlot(row[index].toString())
                        }
                        titleRow[index] == "Weapon Class" -> {
                            itemDTO.setWeaponClass(row[index].toString())
                        }
                        titleRow[index] == "Ammo Type" -> {
                            itemDTO.setAmmoType(row[index].toString())
                        }
                        titleRow[index] == "Vehicle Class" -> {
                            itemDTO.setVehicleClass(row[index].toString())
                        }
                        titleRow[index] == "Num Crew" -> {
                            itemDTO.setNumCrew(row[index].toString())
                        }
                        titleRow[index] == "Primary Armament" -> {
                            itemDTO.setPrimaryArmament(row[index].toString())
                        }
                        titleRow[index] == "Secondary Armament" -> {
                            itemDTO.setSecondaryArmament(row[index].toString())
                        }
                        titleRow[index].startsWith("MPF ") -> {
                            mpfVals.add(row[index].toString())
                        }
                    }
                }

                sortedData[itemDTO.getName()] = itemDTO

            }
        }

        return sortedData
    }

    private fun checkForUpdatesEveryXHours(hours: Long) {
        thread(
            start = true
        ) {
            while (!Thread.currentThread().isInterrupted) {
                Thread.sleep(Duration.ofHours(hours).toMillis())
                this.storedSheetsData = this.sortGoogleSheetsData() as HashMap<String?, ItemDTOInterface>
            }
        }
    }

    init {
        this.storedSheetsData = this.sortGoogleSheetsData() as HashMap<String?, ItemDTOInterface>
    }

    fun getStoredSheetsData(): HashMap<String? ,ItemDTOInterface> {
        return this.storedSheetsData
    }

    fun getPossibleKeys(): List<String> {
        return this.possibleKeys
    }
}