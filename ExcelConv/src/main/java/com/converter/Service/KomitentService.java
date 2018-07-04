package com.converter.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.converter.Model.Komitent;
import com.converter.Repository.KomitentRepository;

@Service
public class KomitentService {
	
	private final KomitentRepository krep;
	
	@Autowired
	public KomitentService(KomitentRepository krep) {
		this.krep = krep;
	}
	
	public List<Komitent> findAll(int offset, int limit, Map<String, Boolean> sortOrders) {
        int page = offset / limit;
        List<Sort.Order> orders = sortOrders.entrySet().stream()
                .map(e -> new Sort.Order(e.getValue() ? Sort.Direction.ASC : Sort.Direction.DESC, e.getKey()))
                .collect(Collectors.toList());

        PageRequest pageRequest = new PageRequest(page, limit, orders.isEmpty() ? null : new Sort(orders));
        List<Komitent> items = krep.findAll(pageRequest).getContent();
        return items.subList(offset%limit, items.size());
    }
	
	 public Integer count() {
	        return Math.toIntExact(krep.count());
	 }
	
	
	public List<Komitent> findAllByTip(String tip){
		return (List<Komitent>) krep.findAllByTipStartsWithIgnoreCase(tip);
	}

}
