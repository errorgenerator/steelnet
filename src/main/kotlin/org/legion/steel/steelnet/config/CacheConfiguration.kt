package org.legion.steel.steelnet.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CacheConfiguration {

    private var initialCapacity = 1000
    private var maximumSize = 10_000L
    private var expireAfterWrite = 4L

    companion object {
        public const val RESPONSE_CACHE: String = "ResponseCache"
    }

    @Bean
    public fun cacheManager(): CacheManager {
        val caffeineCacheManager = CaffeineCacheManager()
        caffeineCacheManager.setCacheNames(
            listOf(
                RESPONSE_CACHE
            )
        )
        caffeineCacheManager.isAllowNullValues = true
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofHours(expireAfterWrite))
            .initialCapacity(this.initialCapacity)
            .maximumSize(this.maximumSize)
        )
        return caffeineCacheManager
    }

}