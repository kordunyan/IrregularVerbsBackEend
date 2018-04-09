package com.kordunyan.repository;

import com.kordunyan.domain.Verb;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VerbRespository extends CrudRepository<Verb, Long> {

	@Query("SELECT v FROM Verb v ORDER BY random()")
	List<Verb> findAllByOrderByIdRandom();

	List<Verb> findAllByOrderByInfinitiveAsc();

}
