package com.converter.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.converter.Model.NalogStavka;

public interface NalogStavkaRepository extends JpaRepository<NalogStavka, Integer>{

	
	List<NalogStavka> findAllByKupacIdAndKupacsifraext(int id, int sifra);
}
