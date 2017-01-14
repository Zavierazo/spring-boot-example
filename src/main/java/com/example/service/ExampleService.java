package com.example.service;

import java.util.UUID;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.enums.CacheEnum;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ExampleService {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private CacheManager cacheManager;

    /**
     * Save result on cache except the result is null or empty
     * Use key = "{#root.methodName,#key1,#key2}" to generate key like [randomStringWithCache,key1,key2]
     * without key element key generated is SimpleKey[key1,key2]
     *
     * Use unless to ignore some cases for cache results
     *
     * @param key1
     * @param key2
     * @return
     */
    @Cacheable(value = CacheEnum.EXAMPLE_CACHE, key = "{#root.methodName,#key1,#key2}", unless = "#result == null or #result.isEmpty()")
    public String randomStringWithCache(String key1, String key2) {
        log.info("Entering to cached method!!!");
        return UUID.randomUUID().toString();
    }
}
