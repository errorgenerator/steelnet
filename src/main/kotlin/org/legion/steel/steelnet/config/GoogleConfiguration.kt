package org.legion.steel.steelnet.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties(prefix = "service.google")
class GoogleConfiguration(
    private var cycle: Long,
    private var id: String,
    private var tokenId: String,
    private var name: String,
    private var tokens: String,
    private var credentials: String
) {



    fun getSpreadSheetId(): String {
        return this.id
    }

    fun getApplicationName(): String {
        return this.name
    }

    fun getTokensDirectory(): String {
        return this.tokens
    }

    fun getCredentialsString(): String {
        return this.credentials
    }

    fun setSpreadSheetId(id: String) {
        this.id = id
    }

    fun setApplicationName(name: String) {
        this.name = name
    }

    fun setTokensDirectory(tokens: String) {
        this.tokens = tokens
    }

    fun getTokenId(): String {
        return this.tokenId
    }

    fun setTokenId(id: String) {
        this.tokenId = id
    }

    fun getCycle(): Long {
        return this.cycle
    }

    fun setCycle(cycle: Long) {
        this.cycle = cycle
    }

}