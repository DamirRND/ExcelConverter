package com.converter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.converter.Model.Komitent;

public interface KomitentRepository extends JpaRepository<Komitent, Integer>{

	List<Komitent> findAllByTipStartsWithIgnoreCase(String tip);
	
}
