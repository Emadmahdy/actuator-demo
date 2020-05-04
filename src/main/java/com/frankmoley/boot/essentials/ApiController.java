package com.frankmoley.boot.essentials;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("api")
public class ApiController {

    private final PresidentRepository presidentRepository;
    private final Counter greetingCounter;
    
    
    public ApiController(PresidentRepository presidentRepository, MeterRegistry registry){
        this.greetingCounter = Counter.builder("api.greeting").register(registry);
		this.presidentRepository = presidentRepository;
    }

    @GetMapping("greeting")
    public String getGreeting(){
    	greetingCounter.increment();
        return "Hello LinkedIn Learning Student";
    }

    @GetMapping("presidents")
    @Timed(value = "api.getAllPresidents")
    public List<President> getAllUSPresidents(){
    	System.out.println("from get all method");
        List<President> presidents = new ArrayList<>();
        this.presidentRepository.findAll().forEach(presidents::add);
        for (Iterator iterator = presidents.iterator(); iterator.hasNext();) {
			President president = (President) iterator.next();
			System.out.println(president.toString());
		}
        return presidents;
    }

}
