package com.converter.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.converter.Model.Ustanova;
import com.converter.Repository.UstanovaRepository;

@Service
public class UstanovaService {

	private final UstanovaRepository urep;
	
	
	@Autowired
	public UstanovaService(UstanovaRepository urep) {
		this.urep = urep;
	}
	
	
	public List<Ustanova> findAll(int offset, int limit, Map<String, Boolean> sortOrders) {
        int page = offset / limit;
        List<Sort.Order> orders = sortOrders.entrySet().stream()
                .map(e -> new Sort.Order(e.getValue() ? Sort.Direction.ASC : Sort.Direction.DESC, e.getKey()))
                .collect(Collectors.toList());

        PageRequest pageRequest = new PageRequest(page, limit, orders.isEmpty() ? null : new Sort(orders));
        List<Ustanova> items = urep.findAll(pageRequest).getContent();
        return items.subList(offset%limit, items.size());
    }
	
	 public Integer count() {
	        return Math.toIntExact(urep.count());
	 }
}
