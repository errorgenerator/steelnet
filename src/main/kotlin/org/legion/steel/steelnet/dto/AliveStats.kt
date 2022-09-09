package org.legion.steel.steelnet.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonRootName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.util.StopWatch
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@JsonRootName("stats")
class AliveStats {


    @JsonProperty("alive_for")
    private var aliveFor: String

    @JsonProperty("database_reachable")
    private var databaseReachable: Boolean

    @JsonProperty("listening_to_servers")
    private var listeningToServers: List<String>

    init {

        aliveFor = "0hrs"

        listeningToServers = listOf()

        databaseReachable = false

    }

    fun getAliveFor(): String {
        return this.aliveFor
    }

    fun getDatabaseReachable(): Boolean {
        return this.databaseReachable
    }

    fun getListeningToServers(): List<String> {
        return this.listeningToServers
    }

}