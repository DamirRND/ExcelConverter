package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.converter.Model.Mesto;
import com.converter.Repository.MestoRepository;

@Service
public class MestoService {
	
	private final MestoRepository mrep;
	
	@Autowired
	public MestoService(MestoRepository mrep) {
		this.mrep = mrep;
	}

	
	public List<Mesto> findAll(){
		return (List<Mesto>) mrep.findAll();
 	}
}
