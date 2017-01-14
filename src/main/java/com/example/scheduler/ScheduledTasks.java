package com.example.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.enums.CacheEnum;
import com.google.common.cache.Cache;

@Component
public class ScheduledTasks {
    @Autowired
    private CacheManager cacheManager;

    /**
     * Starts 60 seconds after startup & every 60 seconds
     *
     * This scheduled task is for force to clean expired elements on cache
     */
    @Scheduled(initialDelay = 60000, fixedRate = 60000) // Every 60 seg
    public void cacheClean() {
        for (String cacheName : cacheManager.getCacheNames()) {
            CacheEnum cacheEnum = CacheEnum.valueOf(cacheName);
            if (cacheEnum.getReadExpiration() > 0 || cacheEnum.getWriteExpiration() > 0 || cacheEnum.getLimit() > 0) {
                GuavaCache genericCache = (GuavaCache) cacheManager.getCache(cacheEnum.name());
                Cache<Object, Object> cache = genericCache.getNativeCache();
                cache.cleanUp();
            }
        }
    }

    @Scheduled(cron = "0 0 4 * * *") // Every day at 4 am
    // @Scheduled(cron = "0 0 10-22 * * *") // Every hour from 10 to 22 h
    public void scheduledExample() {

    }
}
