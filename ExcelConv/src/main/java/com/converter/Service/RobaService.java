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

import com.converter.Model.Roba;
import com.converter.Repository.RobaRepository;

@Service
public class RobaService {

	
	private final RobaRepository robarep;
	
	@Autowired
	public RobaService(RobaRepository robarep) {
		this.robarep = robarep;
	}
	
	private List<Roba> listaJedna;
	
	@Cacheable("roba")
	public List<Roba> findAll(int offset, int limit, Map<String, Boolean> sortOrders) {
        int page = offset / limit;
        List<Sort.Order> orders = sortOrders.entrySet().stream()
                .map(e -> new Sort.Order(e.getValue() ? Sort.Direction.ASC : Sort.Direction.DESC, e.getKey()))
                .collect(Collectors.toList());

        PageRequest pageRequest = new PageRequest(page, limit, orders.isEmpty() ? null : new Sort(orders));
        List<Roba> items = robarep.findAll(pageRequest).getContent();
        return items.subList(offset%limit, items.size());
    }
	
	
	 public Integer count() {
	        return Math.toIntExact(robarep.count());
	 }

	 @CacheEvict(value="roba",allEntries=true)
	 public void removeCache() {
		 System.out.println("Kes izbrisan za robu");
	 }
	 
	 public List<Roba> findAllCombo(){
		 return (List<Roba>) robarep.findAll();
	 }
	 
	 public Roba findOne(Integer id){
		 return robarep.findOne(id);
	 }
	 
	 public Roba save(Roba r){
		return robarep.saveAndFlush(r);
	 }
	 
	 public void delete(Roba r){
		robarep.delete(r);
	 }

	public List<Roba> getListaJedna() {
		return listaJedna;
	}

	public void setListaJedna(List<Roba> listaJedna) {
		this.listaJedna = listaJedna;
	}
	 
	 
		
}
