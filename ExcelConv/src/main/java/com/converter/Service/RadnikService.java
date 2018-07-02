package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	
	public Radnik findOne(String korime, String korlozinka) {
		return radnikrep.findOneByKorimeAndKorlozinka(korime, korlozinka);
	}
	
	
	public List<Radnik> findAll(){
		return (List<Radnik>) radnikrep.findAll();
	}
	
}
