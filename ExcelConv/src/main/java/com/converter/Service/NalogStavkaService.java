package com.converter.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.converter.Model.Komitent;
import com.converter.Model.Nalog;
import com.converter.Model.NalogStavka;
import com.converter.Model.Roba;
import com.converter.Repository.NalogStavkaRepository;

@Service
public class NalogStavkaService {

	
	private final NalogStavkaRepository nlgrep;
	
	@Autowired
	public NalogStavkaService(NalogStavkaRepository nlgrep) {
		this.nlgrep = nlgrep;
	}
	
	
	
	public List<NalogStavka> lista;
	
	@Cacheable("stavka")
	public List<NalogStavka> findAll(int offset, int limit, Map<String, Boolean> sortOrders) {
        int page = offset / limit;
        List<Sort.Order> orders = sortOrders.entrySet().stream()
                .map(e -> new Sort.Order(e.getValue() ? Sort.Direction.ASC : Sort.Direction.DESC, e.getKey()))
                .collect(Collectors.toList());

        PageRequest pageRequest = new PageRequest(page, limit, orders.isEmpty() ? null : new Sort(orders));
        List<NalogStavka> items = nlgrep.findAll(pageRequest).getContent();
        return items.subList(offset%limit, items.size());
    }
	
	
	 public Integer count() {
	        return Math.toIntExact(nlgrep.count());
	 }

	 @CacheEvict(value="roba",allEntries=true)
	 public void removeCache() {
		 System.out.println("Kes izbrisan za robu");
	 }
	 
	public List<NalogStavka> findSveCustom(int id, int sifra){
		setLista(nlgrep.findAllByKupacIdAndKupacsifraext(id, sifra));
		return (List<NalogStavka>) nlgrep.findAllByKupacIdAndKupacsifraext(id, sifra);
	}

	public List<NalogStavka> getLista() {
		return lista;
	}

	public void setLista(List<NalogStavka> lista) {
		this.lista = lista;
	}
	
	
	public List<NalogStavka> findAllByKupac(Komitent k){
		return nlgrep.findAllByKupac(k);
	}
	
	public List<NalogStavka> findAllByRoba(Roba r){
		return nlgrep.findAllByRoba(r);
	}
	public List<NalogStavka> findAllByKupacAndRoba(Komitent k, Roba r){
		return nlgrep.findAllByKupacAndRoba(k, r);
	}
	
	
	public List<NalogStavka> findallByKupacAndNalog(Komitent k, Nalog n){
		return nlgrep.findAllByKupacAndNalog(k, n);
	}
	
	public List<NalogStavka> findallByRobaAndNalog(Roba r, Nalog n){
		return nlgrep.findAllByRobaAndNalog(r, n);
	}
	
	public List<NalogStavka> findallByKupacAndRobaAndNalog(Komitent k, Roba r, Nalog n){
		return nlgrep.findAllByKupacAndRobaAndNalog(k, r, n);
	}
	
	public List<NalogStavka> findallByNalog(Nalog n){
		return nlgrep.findAllByNalog(n);
	}
	
	public NalogStavka save(NalogStavka n )
	{
		return nlgrep.saveAndFlush(n);
	}
	
}
