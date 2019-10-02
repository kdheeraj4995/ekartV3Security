package com.dheeraj.ekartv3.util;

import com.dheeraj.ekartv3.models.ServiceInfo;
import com.google.inject.internal.cglib.core.$CollectionUtils;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

import java.time.Duration;

/**
 * @author Dheeraj Reddy
 */
public class CacheHelper {

    private CacheManager cacheManager;

    private Cache<String, String> publicKeyCache;

    public CacheHelper() {

        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();

        publicKeyCache = cacheManager
                .createCache(
                        "publicKeyCache",
                        CacheConfigurationBuilder
                                .newCacheConfigurationBuilder(
                                        String.class,
                                        String.class,
                                        ResourcePoolsBuilder.heap(10)
                                )
                                .withExpiry(
                                        ExpiryPolicyBuilder.timeToLiveExpiration(
                                                Duration.ofMinutes(60)
                                        )
                                )
                );
    }

    public Cache<String, String> getPublicKeyCache() {
        return publicKeyCache;
    }

    private void setPublicKeyCache(Cache<String, String> publicKeyCache) {
        this.publicKeyCache = publicKeyCache;
    }

    public String getPublicKeyFromService(String service) {
        return getPublicKeyCache().get(service);
    }

    public void addToPublicKeyCache(ServiceInfo serviceInfo) {
        getPublicKeyCache().put(serviceInfo.getService(), serviceInfo.getPublicKey());
    }
}