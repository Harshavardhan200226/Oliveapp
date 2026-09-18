package com.example.demo.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
	
    @GetMapping("/getName")
    public String getName()
    {
    	return "Hi Harsha Welcome To Spring Boot";
    }
    @GetMapping("/{id}")
    public String getId(@PathVariable int id)
    {
    
    	return "Hi Harsha :"+id;
    }
}
