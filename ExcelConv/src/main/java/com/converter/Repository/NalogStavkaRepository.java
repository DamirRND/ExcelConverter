package com.converter.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.converter.Model.Komitent;
import com.converter.Model.Nalog;
import com.converter.Model.NalogStavka;
import com.converter.Model.Roba;

@SuppressWarnings("rawtypes")
public interface NalogStavkaRepository extends JpaRepository<NalogStavka, Integer>, JpaSpecificationExecutor{

	List<NalogStavka> findAllByKupacIdAndKupacsifraext(int id, int sifra);

	
	List<NalogStavka> findAllByKupac(Komitent k);
	List<NalogStavka> findAllByKupacAndRoba(Komitent k, Roba r);
	List<NalogStavka> findAllByRoba(Roba r);
	
	List<NalogStavka> findAllByKupacAndNalog(Komitent k, Nalog n);
	List<NalogStavka> findAllByRobaAndNalog(Roba r, Nalog n);
	List<NalogStavka> findAllByKupacAndRobaAndNalog(Komitent k, Roba r, Nalog n);
	
	List<NalogStavka> findAllByNalog(Nalog n);
	
	
	@Transactional
	@Query("select c from NalogStavka c where c.nalog=:nalog and c.kupac is null and c.roba is null")
	List<NalogStavka> listaNeobradjenih(@Param("nalog") Nalog n);
	
	@Transactional
	@Query("select c from NalogStavka c where c.nalog=:nalog and c.kupac is not null and c.roba is not null")
	List<NalogStavka> listaObradjenih(@Param("nalog") Nalog n);
}
