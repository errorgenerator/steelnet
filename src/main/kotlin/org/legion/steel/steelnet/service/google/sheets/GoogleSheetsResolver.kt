package org.legion.steel.steelnet.service.google.sheets

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import com.google.api.client.util.store.FileDataStoreFactory
import com.google.api.services.sheets.v4.Sheets
import com.google.api.services.sheets.v4.SheetsScopes
import org.legion.steel.steelnet.dto.ItemDTOInterface
import org.springframework.stereotype.Service
import java.io.File
import java.util.*
import kotlin.collections.HashMap


@Service
class GoogleSheetsResolver {

    private val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
    private val spreadSheetId = "1d9Ti9PxdcqeHPGnOejWWeYdTX-YdREpmeg1uBPROZfw"

    private val applicationName = "SteelNet"
    private val jsonFactory = GsonFactory.getDefaultInstance()
    private val tokensDirectoryPath = "/home/tilman/IdeaProjects/steelnet/src/main/resources/"
    private val credentialsFilePath = "/hom/tilman/IdeaProjects/steelnet/resources/google/credentials.json"
    private val scopes = Collections.singletonList(SheetsScopes.SPREADSHEETS)

    private lateinit var storedSheetsData: HashMap<String, List<ItemDTOInterface>>

    private fun getCredentials(httpTransport: NetHttpTransport): Credential {
        val readCredentials = File(this.credentialsFilePath).reader()
        val clientSecrets = GoogleClientSecrets.load(this.jsonFactory, readCredentials)

        val flow = GoogleAuthorizationCodeFlow.Builder(
            httpTransport,
            this.jsonFactory,
            clientSecrets,
            this.scopes
        )
            .setDataStoreFactory(FileDataStoreFactory(File(this.tokensDirectoryPath)))
            .setAccessType("offline")
            .build()
        val receiver = LocalServerReceiver.Builder().setPort(8888).build()
        return AuthorizationCodeInstalledApp(flow, receiver).authorize("user")

    }

    private fun fetchGoogleSheetsData(): List<List<Any>>? {
        val service = Sheets.Builder(this.httpTransport, this.jsonFactory, this.getCredentials(this.httpTransport))
            .setApplicationName(this.applicationName)
            .build()
        val response = service.spreadsheets().values().get(this.spreadSheetId, "")
            .execute()
        return response.getValues()
    }


    private fun sortGoogleSheetsData(): HashMap<String, List<ItemDTOInterface>> {

        val sortedData: HashMap<String, List<ItemDTOInterface>> = HashMap<String, List<ItemDTOInterface>>()

        val itemTypes: List<String> = listOf(
            "vehicle",
            "weapon",
            "equipment",
            "deployable"
        )

        val fetchedData = this.fetchGoogleSheetsData()

        if (!fetchedData.isNullOrEmpty()) {

            fetchedData.forEach{
                it.forEach{ ot ->
                val tmpData = ot.toString()
            }}

        }

        return sortedData
    }

    init {
        this.storedSheetsData = this.sortGoogleSheetsData()
    }
}