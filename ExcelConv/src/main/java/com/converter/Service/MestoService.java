package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

	public List<Mesto> listaJedan;
	
	@Cacheable("mesto")
	public List<Mesto> findAll(){
		return (List<Mesto>) mrep.findAll();
 	}
	
	@CacheEvict(value="mesto", allEntries=true)
	public void izbrisiCache() {
		System.out.println("Kes mesto izbrisan.");
	}

	public Mesto findOne(Integer id){
		 return mrep.findOne(id);
	 }
	 
	 public Mesto save(Mesto r){
		return mrep.saveAndFlush(r);
	 }
	 
	 public void delete(Mesto r){
		 mrep.delete(r);
	 }
	
	
	public List<Mesto> getListaJedan() {
		return listaJedan;
	}

	public void setListaJedan(List<Mesto> listaJedan) {
		this.listaJedan = listaJedan;
	}
	
	
}
