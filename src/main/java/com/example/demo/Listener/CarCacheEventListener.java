package com.example.demo.Listener;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Car;

@Component
public class CarCacheEventListener implements CacheEventListener<String, Car>{

	@Override
	public void onEvent(CacheEvent<? extends String, ? extends Car> event) {
		System.out.println(event.getKey()+" App EventListener Executed sucessfully....  "+ event.getType()+"\n");
		
	}

}
