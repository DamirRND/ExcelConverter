package com.converter.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.converter.Model.Komitent;
import com.converter.Model.Nalog;
import com.converter.Repository.NalogRepository;

@Service
public class NalogService {

	
	private final NalogRepository nrep;
	
	
	public List<Nalog> zaPretragu;
	
	@Autowired
	public NalogService(NalogRepository nrep) {
		this.nrep = nrep;
	}
	
	public List<Nalog> findAll(){
		return (List<Nalog>) nrep.findAll();
	}
	
	public Nalog findOneNalog(int id, int status, LocalDate datum, int mesec, Komitent k) {
		return nrep.findOneByIdAndStatusAndDatumAndMesecAndKomitent(id, status, datum, mesec, k);
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
	 
	 public Nalog findZaExport(LocalDate datum, int mesec, Komitent k) {
		 return nrep.findOneByDatumAndMesecAndKomitent(datum, mesec, k);
	 }
	 
	 public Nalog findOneByFajl(String name) {
		 return nrep.findOneByIzvornifajl(name);
	 }
	 
	 @Cacheable("nalozi")
	 public List<Nalog> findSve(int status){
		 return (List<Nalog>) nrep.findAllOtvorene(status);
	 }
	 
	 @CacheEvict(value="nalozi",allEntries=true)
	 public void removeCache() {
		 System.out.println("Kes izbrisan za naloge");
	 }

	public List<Nalog> getZaPretragu() {
		return zaPretragu;
	}

	public void setZaPretragu(List<Nalog> zaPretragu) {
		this.zaPretragu = zaPretragu;
	}
	 
	 
	public int pronadjiMax(LocalDate datum,int mesec,Komitent id, int status ) {
		return nrep.getMaxId(datum, mesec, id, status);
	}
}
