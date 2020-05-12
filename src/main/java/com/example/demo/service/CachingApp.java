package com.example.demo.service;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.Book;
import com.example.demo.dto.Car;

@Service
public class CachingApp {
	
	//*********************Cache to maintain Book Information********************
	
	//This method is to updated Book information in Cache
	@CachePut(cacheNames = "mycacheBook", key="'Book'", unless = "#result instanceof T(java.lang.Exception)")
	public Book updateBook(int id, String bookName) {
		System.out.println("Executing updateBook method...");	
		Book book = new Book();
		book.setId(id);
		book.setName(bookName);
		return book;
	}
	
	//This method is to get Book information from Cache
	@Cacheable(cacheNames = "mycacheBook", key="'Book'", unless = "#result instanceof T(java.lang.Exception)")
	public Book getBook() {
		System.out.println("\nExecuting getBook method...");
		Book book = new Book();
		book.setId(10);		
		book.setName("Mahabharat");
		return book;
	}
	
	
	//*********************Cache to maintain Car Information********************
	
	//This method is to updated Car information in Cache
		@CachePut(cacheNames = "mycacheCar", key="'Car'", unless = "#result instanceof T(java.lang.Exception)")
		public Car updateCar(int id, String carName) {
			System.out.println("Executing updateCar method...");	
			Car car = new Car();
			car.setId(id);
			car.setName(carName);
			return car;
		}
		
		//This method is to get Car information from Cache
		@Cacheable(cacheNames = "mycacheCar", key="'Car'", unless = "#result instanceof T(java.lang.Exception)")
		public Car getCar() {
			System.out.println("\nExecuting getCar method...");
			Car car = new Car();
			car.setId(30);		
			car.setName("Audi");
			return car;
		}
}