package org.legion.steel.steelnet

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class SteelNetApplication

fun main(args: Array<String>) {
    runApplication<SteelNetApplication>(*args)
}
