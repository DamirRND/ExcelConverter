package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.converter.Model.Entitet;
import com.converter.Repository.EntitetRepository;

@Service
public class EntitetService {

	private final EntitetRepository erep;
	
	@Autowired
	public EntitetService(EntitetRepository erep) {
		this.erep =erep;
	}
	
	
	public List<Entitet> findAll(){
		return (List<Entitet>) erep.findAll();
	}
}
