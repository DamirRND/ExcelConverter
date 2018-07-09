package com.converter.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.converter.Model.MapaRobe;



public interface MapaRobeRepository extends JpaRepository<MapaRobe, Integer>{
	
	MapaRobe findOneByRobaSifraExtAndRobaNazivExtAndVpid(int sifra,String naziv, int vp);
	

}
