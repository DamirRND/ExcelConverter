package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.converter.Model.Entitet;
import com.converter.Model.Region;
import com.converter.Repository.RegionRepository;

@Service
public class RegionService {

	
	private final RegionRepository rrep;
	
	
	@Autowired
	public RegionService(RegionRepository rrep) {
		this.rrep = rrep;
	}
	public List<Region> listaJedan;
	
	@Cacheable("region")
	public List<Region> findAll(){
		return (List<Region>) rrep.findAll();
	}
	
	@CacheEvict(value="region", allEntries=true)
	public void izbrisiCache() {
		System.out.println("Region kes uspjesno izbrisan.");
	}

	 public Region findOne(Integer id){
		 return rrep.findOne(id);
	 }
	 
	 public Region save(Region r){
		return rrep.saveAndFlush(r);
	 }
	 
	 public void delete(Region r){
		 rrep.delete(r);
	 }
	
	 

	public List<Region> getListaJedan() {
		return listaJedan;
	}


	public void setListaJedan(List<Region> listaJedan) {
		this.listaJedan = listaJedan;
	}
	
	
	
}
