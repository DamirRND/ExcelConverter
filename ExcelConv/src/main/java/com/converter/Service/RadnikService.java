package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.converter.Model.Radnik;
import com.converter.Repository.RadnikRepository;

@Service
public class RadnikService {

	
	private RadnikRepository radnikrep;
	
	
	@Autowired
	public RadnikService(RadnikRepository radnikrep) {
		this.radnikrep = radnikrep;
	}
	
	public List<Radnik> listaJedan;
	
	
	public Radnik findOne(String korime, String korlozinka) {
		return radnikrep.findOneByKorimeAndKorlozinka(korime, korlozinka);
	}
	
	@Cacheable("radnici")
	public List<Radnik> findAll(){
		return (List<Radnik>) radnikrep.findAll();
	}
	
	@CacheEvict(value="radnici", allEntries=true)
	public void izbrisiCache() {
		System.out.println("Radnici kes izbrisan.");
	}
	
	 public Radnik findOne(Integer id){
		 return radnikrep.findOne(id);
	 }
	 
	 public Radnik save(Radnik r){
		return radnikrep.saveAndFlush(r);
	 }
	 
	 public void delete(Radnik r){
		 radnikrep.delete(r);
	 }


	public List<Radnik> getListaJedan() {
		return listaJedan;
	}


	public void setListaJedan(List<Radnik> listaJedan) {
		this.listaJedan = listaJedan;
	}
	
	
	
}
