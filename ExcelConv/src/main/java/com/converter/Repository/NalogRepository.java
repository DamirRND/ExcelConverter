package com.converter.Repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.converter.Model.Nalog;

public interface NalogRepository extends JpaRepository<Nalog, Integer>{

	
	public Nalog findOneByStatusAndDatumAndMesec(int status, LocalDate datum, int mesec);
	
	@Transactional
	@Query("select n from Nalog n where n.datum = :datum and n.mesec=:mesec and n.status != :status")
    public Nalog findOneByDatumAndMesecAndStatusNotEqual(@Param("datum") LocalDate datum,@Param("mesec") int mesec,@Param("status") int status );
}
