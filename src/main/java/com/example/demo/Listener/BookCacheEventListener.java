package com.example.demo.Listener;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Component;

import com.example.demo.dto.Book;

@Component
public class BookCacheEventListener implements CacheEventListener<String, Book>{

	@Override
	public void onEvent(CacheEvent<? extends String, ? extends Book> event) {
		System.out.println(event.getKey()+" App EventListener Executed sucessfully....  "+ event.getType()+"\n");
		
	}

}
