package com.converter.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.converter.Model.MapaKupca;
import com.converter.Repository.MapaKupacRepository;

@Service
public class MapaKupacService {

	
	
	private final MapaKupacRepository mpKupac;
	
	@Autowired
	public MapaKupacService(MapaKupacRepository mpKupac)
	{
		this.mpKupac=mpKupac;
		
	}
	
	public MapaKupca findOne(int sifra,String naziv, int vp)
	{
		return mpKupac.findOneByKupacSifraExtAndKupacNazivExtAndVpid(sifra, naziv, vp);
	}
}
