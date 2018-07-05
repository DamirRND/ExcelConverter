package com.converter.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.converter.Model.Komitent;
import com.converter.Model.Nalog;
import com.converter.Repository.NalogRepository;

@Service
public class NalogService {

	
	private final NalogRepository nrep;
	
	
	@Autowired
	public NalogService(NalogRepository nrep) {
		this.nrep = nrep;
	}
	
	public List<Nalog> findAll(){
		return (List<Nalog>) nrep.findAll();
	}
	
	public Nalog findOneNalog(int status, LocalDate datum, int mesec, Komitent k) {
		return nrep.findOneByStatusAndDatumAndMesecAndKomitent(status, datum, mesec, k);
	}
	
	public Nalog provjeraNaloga(LocalDate datum, int mesec,Komitent id, int status) {
		return nrep.findOneByDatumAndMesecAndStatusNotEqual(datum, mesec, id,status);
	}
	
	public Nalog findOne(Integer id){
		 return nrep.findOne(id);
	 }
	 
	 public Nalog save(Nalog r){
		return nrep.saveAndFlush(r);
	 }
	 
	 public void delete(Nalog r){
		 nrep.delete(r);
	 }
	 
	 
	 public Nalog findOneByFajl(String name) {
		 return nrep.findOneByIzvornifajl(name);
	 }
	
}
