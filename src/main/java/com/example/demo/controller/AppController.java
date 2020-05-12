package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Book;
import com.example.demo.dto.Car;
import com.example.demo.service.CachingApp;

@RestController
@RequestMapping("/bookApp")
public class AppController {
	
	@Autowired
	private CachingApp cachingApp;
	
	@GetMapping(path="/getBookFromCache")
	public void getBookFromCache() {
		
				Book book=null;
		
				//Calling getBook() method first time.
				book=cachingApp.getBook();
				System.out.println(book.getId()+"\t"+book.getName()+"\n");
				
				//Calling getBook() method second time.
				book=cachingApp.getBook();
				System.out.println(book.getId()+"\t"+book.getName()+"\n");
				
				//Calling updateBook() method first time.
				cachingApp.updateBook(20, "Ramayan");
				
				//Again calling getBook() method to check what value inside the cache.
				book=cachingApp.getBook();
				System.out.println(book.getId()+"\t"+book.getName()+"\n");
				
				//Calling updateBook() method second time but with same information.
				cachingApp.updateBook(20, "Ramayan");
				
				//Again calling getBook() method to check what value inside the cache.
				book=cachingApp.getBook();
				System.out.println(book.getId()+"\t"+book.getName()+"\n");
				
								
	}
	
	@GetMapping(path="/getCarFromCache")
	public void getCarFromCache() {
		
				Car car=null;
				
				//Calling getCar() method first time.
				car=cachingApp.getCar();
				System.out.println(car.getId()+"\t"+car.getName()+"\n");
				
				//Calling getCar() method second time.
				car=cachingApp.getCar();
				System.out.println(car.getId()+"\t"+car.getName()+"\n");
				
				//Calling updateCar() method first time.
				cachingApp.updateCar(40, "BMW");
				
				//Again calling getCar() method to check what value inside the cache.
				car=cachingApp.getCar();
				System.out.println(car.getId()+"\t"+car.getName()+"\n");
				
				//Calling updateCar() method second time.
				cachingApp.updateCar(50, "Mercedes");
				
				//Again calling getCar() method to check what value inside the cache.
				car=cachingApp.getCar();
				System.out.println(car.getId()+"\t"+car.getName()+"\n");
				
	}
}
