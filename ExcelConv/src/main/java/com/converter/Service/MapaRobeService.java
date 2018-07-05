package com.converter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.converter.Model.MapaRobe;
import com.converter.Repository.MapaRobeRepository;

@Service
public class MapaRobeService {
	
	
	private final MapaRobeRepository mpRepository;
	
	
	@Autowired
	public MapaRobeService(MapaRobeRepository mpRepository)
	{
		this.mpRepository=mpRepository;
		
	}
	
	public MapaRobe findOne(int sifra,String naziv, int vp)
	{
		return mpRepository.findOneByRobasifraextAndRobanazivextAndVpid(sifra, naziv, vp);
	}

}
