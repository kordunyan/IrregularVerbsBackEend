package com.kordunyan.service.impl;

import com.kordunyan.domain.Verb;
import com.kordunyan.repository.VerbRespository;
import com.kordunyan.service.VerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VerbServiceImpl implements VerbService {

	private final VerbRespository verbRespository;

	@Autowired
	public VerbServiceImpl(VerbRespository verbRespository) {
		this.verbRespository = verbRespository;
	}

	public List<Verb> getAllRandomVerbs() {
		return verbRespository.findAllByOrderByIdRandom();
	}

	@Override
	public List<Verb> getAllOrderedVerbs() {
		return verbRespository.findAllByOrderByInfinitiveAsc();
	}

	public Verb saveVerb(Verb verb) {
		return verbRespository.save(verb);
	}

}
