package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.converter.Model.KorGrupa;
import com.converter.Model.Radnik;
import com.converter.Repository.KorGrupaRepository;

@Service
public class KorGrupaService {

	private KorGrupaRepository korrep;
	
	@Autowired
	public KorGrupaService(KorGrupaRepository korrep) {
		this.korrep = korrep;
	}
	
	public List<KorGrupa> listaJedan;
	
	@Cacheable("korgrupa")
	public List<KorGrupa> findAll() {
		return (List<KorGrupa>) korrep.findAll();
	}
	
	@CacheEvict(value="korgrupa", allEntries=true)
	public void izbrisiCache() {
		System.out.println("Kes kor grupa izbrisan.");
	}

	public KorGrupa findOne(Integer id){
		 return korrep.findOne(id);
	 }
	 
	 public KorGrupa save(KorGrupa r){
		return korrep.saveAndFlush(r);
	 }
	 
	 public void delete(KorGrupa r){
		 korrep.delete(r);
	 }
	
	public List<KorGrupa> getListaJedan() {
		return listaJedan;
	}

	public void setListaJedan(List<KorGrupa> listaJedan) {
		this.listaJedan = listaJedan;
	}
	
	
	
}
