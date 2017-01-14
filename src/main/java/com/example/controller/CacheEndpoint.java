package com.example.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.cache.Cache;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/cache")
@Slf4j
public class CacheEndpoint {
    @Autowired
    private CacheManager cacheManager;

    @RequestMapping(method = RequestMethod.GET, value = "/clearAll", produces = {
            MediaType.TEXT_PLAIN_VALUE
    })
    @ResponseBody
    public String clearAll() throws Exception {
        for (String cacheName : cacheManager.getCacheNames()) {
            GuavaCache guavaCache = (GuavaCache) cacheManager.getCache(cacheName);
            Cache<Object, Object> cache = guavaCache.getNativeCache();
            cache.invalidateAll();
        }
        return "All caches cleared!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cacheName}/clear", produces = {
            MediaType.TEXT_PLAIN_VALUE
    })
    @ResponseBody
    public String clear(@PathVariable String cacheName) throws Exception {
        GuavaCache guavaCache = (GuavaCache) cacheManager.getCache(cacheName);
        Cache<Object, Object> cache = guavaCache.getNativeCache();
        cache.invalidateAll();
        return "Cache " + cacheName + " cleared!";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/stats", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public Map<String, Integer> stats() throws Exception {
        Map<String, Integer> stats = new HashMap<>();
        for (String cacheName : cacheManager.getCacheNames()) {
            GuavaCache guavaCache = (GuavaCache) cacheManager.getCache(cacheName);
            Cache<Object, Object> cache = guavaCache.getNativeCache();
            stats.put(cacheName, cache.asMap().size());
        }
        return stats;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cacheName}/keys", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public List<String> keys(@PathVariable String cacheName) throws Exception {
        GuavaCache guavaCache = (GuavaCache) cacheManager.getCache(cacheName);
        Cache<Object, Object> cache = guavaCache.getNativeCache();
        List<String> keys = new ArrayList<>();
        cache.asMap().keySet().stream().forEach(key -> keys.add(key.toString()));
        return keys;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cacheName}/values", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public Map<Object, Object> values(@PathVariable String cacheName) throws Exception {
        GuavaCache guavaCache = (GuavaCache) cacheManager.getCache(cacheName);
        Cache<Object, Object> cache = guavaCache.getNativeCache();
        return cache.asMap();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cacheName}/get/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public Object get(@PathVariable String cacheName, @PathVariable String id) throws Exception {
        GuavaCache guaveCache = (GuavaCache) cacheManager.getCache(cacheName);
        Cache<Object, Object> cache = guaveCache.getNativeCache();
        return cache.asMap().get(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{cacheName}/delete/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ResponseBody
    public String delete(@PathVariable String cacheName, @PathVariable String id) throws Exception {
        GuavaCache guavaCache = (GuavaCache) cacheManager.getCache(cacheName);
        Cache<Object, Object> cache = guavaCache.getNativeCache();
        cache.invalidate(id);
        return "DONE!";
    }
}
