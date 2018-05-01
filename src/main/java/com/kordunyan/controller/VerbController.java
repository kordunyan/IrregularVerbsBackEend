package com.kordunyan.controller;


import com.kordunyan.domain.Verb;
import com.kordunyan.service.VerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
	public ResponseEntity addVerb(@RequestBody Verb verb) {
		try {
			return ResponseEntity.ok(verbService.saveVerb(verb));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@DeleteMapping("/verb/{verbId}")
	public ResponseEntity deleteVerb(@PathVariable("verbId") Long verbId) {
		verbService.deleteById(verbId);
		return ResponseEntity.ok(true);
	}
}
