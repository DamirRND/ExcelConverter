package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.converter.Model.RobaGrupa;
import com.converter.Repository.RobaGrupaRepository;

@Service
public class RobaGrupaService {

	
	private final RobaGrupaRepository rgRep;
	
	@Autowired
	public RobaGrupaService(RobaGrupaRepository rgRep) {
		this.rgRep = rgRep;
	}
	
	List<RobaGrupa> listaJedna;
	
	@Cacheable("robaGrupe")
	public List<RobaGrupa> findAll(){
		setListaJedna(rgRep.findAll());
		return (List<RobaGrupa>) rgRep.findAll();
	}
	
	 public RobaGrupa save(RobaGrupa r){
		return rgRep.saveAndFlush(r);
	}
		 
	 public void delete(RobaGrupa r){
		 rgRep.delete(r);
	}
		 
	
	public RobaGrupa findOne(Integer id){
		 return rgRep.findOne(id);
	}


	public List<RobaGrupa> getListaJedna() {
		return listaJedna;
	}


	public void setListaJedna(List<RobaGrupa> listaJedna) {
		this.listaJedna = listaJedna;
	}
	
	
	
}
