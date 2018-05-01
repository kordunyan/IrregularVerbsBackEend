package com.kordunyan.service.impl;

import com.kordunyan.domain.Verb;
import com.kordunyan.repository.VerbRespository;
import com.kordunyan.service.VerbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class VerbServiceImpl implements VerbService {

	private final VerbRespository verbRespository;
	private final ImageService imageService;

	@Autowired
	public VerbServiceImpl(VerbRespository verbRespository, ImageService imageService) {
		this.verbRespository = verbRespository;
		this.imageService = imageService;
	}

	public List<Verb> getAllRandomVerbs() {
		return verbRespository.findAllByOrderByIdRandom();
	}

	@Override
	public List<Verb> getAllOrderedVerbs() {
		return verbRespository.findAllByOrderByInfinitiveAsc();
	}

	public Verb saveVerb(Verb verb) throws IOException {
		verb.setImage(imageService.saveImage(verb.getImage()));
		return verbRespository.save(verb);
	}

	@Override
	public void deleteById(Long id) {
		this.verbRespository.deleteById(id);
	}

}
