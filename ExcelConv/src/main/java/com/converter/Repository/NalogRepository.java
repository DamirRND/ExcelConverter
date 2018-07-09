package com.converter.Repository;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.converter.Model.Komitent;
import com.converter.Model.Nalog;

public interface NalogRepository extends JpaRepository<Nalog, Integer>{

	@Transactional
	@Query("select n from Nalog n where n.id=:id and n.datum = :datum and n.mesec=:mesec and n.komitent=:komitent and n.status != :status")
	public Nalog findOneByIdAndStatusAndDatumAndMesecAndKomitent(@Param("id") int id, @Param("status") int status, @Param("datum") LocalDate datum,@Param("mesec") int mesec,@Param("komitent") Komitent k);
	
	public Nalog findOneByIzvornifajl(String name);
	
	public Nalog findOneByDatumAndMesecAndKomitent(LocalDate datum, int mesec, Komitent k);
	
	@Query("SELECT max(c.id) FROM Nalog c where c.datum = :datum and c.mesec=:mesec and c.komitent=:komitent and c.status != :status")
	public int getMaxId(@Param("datum") LocalDate datum,@Param("mesec") int mesec,@Param("komitent") Komitent id, @Param("status") int status );
	
	@Transactional
	@Query("select n from Nalog n where n.datum = :datum and n.mesec=:mesec and n.komitent=:komitent and n.status != :status")
    public Nalog findOneByDatumAndMesecAndStatusNotEqual(@Param("datum") LocalDate datum,@Param("mesec") int mesec,@Param("komitent") Komitent id, @Param("status") int status );
	
	
	@Transactional
	@Query("select n from Nalog n where n.status != :status")
    public List<Nalog> findAllOtvorene(@Param("status") int status );
	
}
