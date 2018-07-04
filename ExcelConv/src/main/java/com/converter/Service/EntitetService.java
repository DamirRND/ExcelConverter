package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.converter.Model.Entitet;
import com.converter.Model.Roba;
import com.converter.Repository.EntitetRepository;

@Service
public class EntitetService {

	private final EntitetRepository erep;
	
	@Autowired
	public EntitetService(EntitetRepository erep) {
		this.erep =erep;
	}
	
	
	List<Entitet> listaJedan;
	
	@Cacheable("entitet")
	public List<Entitet> findAll(){
		return (List<Entitet>) erep.findAll();
	}
	
	@CacheEvict(value="entitet", allEntries=true)
	public void izbrisiCache() {
		System.out.println("Kes entitet izbrisan");
	}
	 public Entitet findOne(Integer id){
		 return erep.findOne(id);
	 }
	 
	 public Entitet save(Entitet r){
		return erep.saveAndFlush(r);
	 }
	 
	 public void delete(Entitet r){
		 erep.delete(r);
	 }
	

	public List<Entitet> getListaJedan() {
		return listaJedan;
	}

	public void setListaJedan(List<Entitet> listaJedan) {
		this.listaJedan = listaJedan;
	}
	
	
}
