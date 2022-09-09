package org.legion.steel.steelnet.controller

import org.legion.steel.steelnet.dto.AliveStats
import org.legion.steel.steelnet.service.util.AliveService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class CheckAliveController {

    @Autowired
    private lateinit var aliveService: AliveService


    @GetMapping("/alive", produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun getAliveStats(): ResponseEntity<AliveStats> {
        return this.aliveService.createAliveStats()
    }
}