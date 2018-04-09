package com.kordunyan.controller;


import com.kordunyan.domain.Verb;
import com.kordunyan.service.VerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class VerbController {

	private final VerbService verbService;

	@Autowired
	public VerbController(VerbService verbService) {
		this.verbService = verbService;
	}

	@GetMapping("/verbs")
	public List<Verb> getAllVerbs() {
		return verbService.getAllOrderedVerbs();
	}

	@GetMapping("/verbs/order/random")
	public List<Verb> getAllVerbsRandom() {
		return verbService.getAllRandomVerbs();
	}

	@PostMapping("/verb")
	public Verb addVerb(@RequestBody Verb verb) {
		return verbService.saveVerb(verb);
	}

}
