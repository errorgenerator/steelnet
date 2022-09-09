package org.legion.steel.steelnet.service.util

import org.legion.steel.steelnet.dto.AliveStats
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class AliveService {

    public fun createAliveStats(): ResponseEntity<AliveStats> {
        val stats = AliveStats()
        return ResponseEntity.ok(stats)
    }
}