package com.example.demo.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import javax.cache.CacheManager;
import javax.cache.Caching;

import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.ResourcePools;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.config.DefaultConfiguration;
import org.ehcache.event.EventType;
import org.ehcache.expiry.ExpiryPolicy;
import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.demo.Listener.BookCacheEventListener;
import com.example.demo.Listener.CarCacheEventListener;
import com.example.demo.dto.Book;
import com.example.demo.dto.Car;

@Configuration
@ComponentScan("com.example.demo")
@EnableCaching  //enables Spring’s annotation-driven cache management capability
public class AppConfig {

	@Autowired
	private BookCacheEventListener bookCacheEventListener;
	
	@Autowired
	private CarCacheEventListener carCacheEventListener;

	/**
     * This method returns the cache manager after creating the Book and Car caches with the required configuration
     * 
     * @return CacheManager
     */
	@Bean
	public CacheManager cacheManagerBean() {
		String cacheBook = "mycacheBook";
		String cacheCar = "mycacheCar";
		Map<String, CacheConfiguration<?, ?>> cache = new HashMap<>();

		cache.put(cacheBook, getBookCacheConfiguration());
		cache.put(cacheCar, getCarCacheConfiguration());

		//configuration of cacheManager.
		EhcacheCachingProvider provider = (EhcacheCachingProvider) Caching.getCachingProvider();
		org.ehcache.config.Configuration configuration = new DefaultConfiguration(cache,
				provider.getDefaultClassLoader());

		CacheManager cacheManager = provider.getCacheManager(provider.getDefaultURI(), configuration);
		cacheManager.enableStatistics(cacheBook, true);
		cacheManager.enableStatistics(cacheCar, true);
		return cacheManager;
	}

	/**
     * This method creates the cache configuration by defining various factors like expiry of cache, heap size in MB's
     * 
     * @return {@code CacheConfiguration<String, Book>}
     */
	@SuppressWarnings("deprecation")
	private CacheConfiguration<String, Book> getBookCacheConfiguration() {

		ResourcePools cacheResourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder().heap(1L, MemoryUnit.MB)
				.build();

		ExpiryPolicy<Object, Object> expiry = ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofHours(2));

		CacheEventListenerConfigurationBuilder listener = CacheEventListenerConfigurationBuilder
				.newEventListenerConfiguration(bookCacheEventListener, EventType.CREATED, EventType.UPDATED).unordered()
				.asynchronous();

		return CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, Book.class, cacheResourcePools).withExpiry(expiry)
				.add(listener).build();

	}

	/**
     * This method creates the cache configuration by defining various factors like expiry of cache, heap size in MB's
     * 
     * @return {@code CacheConfiguration<String, Car>}
     */
	@SuppressWarnings("deprecation")
	private CacheConfiguration<String, Car> getCarCacheConfiguration() {

		ResourcePools cacheResourcePools = ResourcePoolsBuilder.newResourcePoolsBuilder().heap(1L, MemoryUnit.MB)
				.build();

		ExpiryPolicy<Object, Object> expiry = ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofHours(2));

		CacheEventListenerConfigurationBuilder listener = CacheEventListenerConfigurationBuilder
				.newEventListenerConfiguration(carCacheEventListener, EventType.CREATED, EventType.UPDATED).unordered()
				.asynchronous();

		return CacheConfigurationBuilder
				.newCacheConfigurationBuilder(String.class, Car.class, cacheResourcePools).withExpiry(expiry)
				.add(listener).build();

	}
}
