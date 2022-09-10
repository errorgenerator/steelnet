package org.legion.steel.steelnet.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Configuration


@ConstructorBinding
@ConfigurationProperties(prefix = "service.google")
class GoogleConfiguration(
    private var id: String,
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

    fun getCredentialsFilePath(): String {
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


}