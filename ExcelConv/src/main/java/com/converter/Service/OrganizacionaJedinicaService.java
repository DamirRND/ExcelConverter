package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.converter.Model.OrganizacionaJedinica;
import com.converter.Repository.OrganizacionaJedinicaRep;

@Service
public class OrganizacionaJedinicaService {

	
	private final OrganizacionaJedinicaRep orgrep;
	
	
	@Autowired
	public OrganizacionaJedinicaService(OrganizacionaJedinicaRep orgrep) {
		this.orgrep = orgrep;
	}
	
	public List<OrganizacionaJedinica> listaJedan;
	
	@Cacheable("orgjed")
	public List<OrganizacionaJedinica> findAll(){
		return (List<OrganizacionaJedinica>) orgrep.findAll();
	}
	
	@CacheEvict(value="orgjed", allEntries=true)
	public void izbrisiCache() {
		System.out.println("Org. jed. kes izbrisan.");
	}
	
	 public OrganizacionaJedinica findOne(Integer id){
		 return orgrep.findOne(id);
	 }
	 
	 public OrganizacionaJedinica save(OrganizacionaJedinica r){
		return orgrep.saveAndFlush(r);
	 }
	 
	 public void delete(OrganizacionaJedinica r){
		 orgrep.delete(r);
	 }
	
	 

	public List<OrganizacionaJedinica> getListaJedan() {
		return listaJedan;
	}

	public void setListaJedan(List<OrganizacionaJedinica> listaJedan) {
		this.listaJedan = listaJedan;
	}
	
	
}
