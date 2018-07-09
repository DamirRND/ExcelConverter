package com.converter.Model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateConverter;

@SuppressWarnings("serial")
@Entity
@Table(name="nalog")
@NamedQuery(name="Nalog.findAll", query="SELECT n FROM Nalog n")
public class Nalog implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="sifra")
	private Integer sifra;
	
	
	@Column(name="datum")
	@Convert(converter = LocalDateConverter.class)
	private LocalDate datum;
	
	
	@Column(name="izvornifajl")
	private String izvorniFajl;
	
	@Column(name="napomena")
	private String napomena;


	@ManyToOne
	private OrganizacionaJedinica orgjed;
	
	@ManyToOne
	private Komitent komitent;
	
	@Column(name="mesec")
	private Integer mesec;

	public Nalog() {}

	public Nalog(Integer id, Integer status, Integer sifra, LocalDate datum, String izvorniFajl, String napomena,
			OrganizacionaJedinica orgjed, Komitent komitent, Integer mesec) {
		super();
		this.id = id;
		this.status = status;
		this.sifra = sifra;
		this.datum = datum;
		this.izvorniFajl = izvorniFajl;
		this.napomena = napomena;
		this.orgjed = orgjed;
		this.komitent = komitent;
		this.mesec = mesec;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSifra() {
		return sifra;
	}

	public void setSifra(Integer sifra) {
		this.sifra = sifra;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public String getIzvorniFajl() {
		return izvorniFajl;
	}

	public void setIzvorniFajl(String izvorniFajl) {
		this.izvorniFajl = izvorniFajl;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public OrganizacionaJedinica getOrgjed() {
		return orgjed;
	}

	public void setOrgjed(OrganizacionaJedinica orgjed) {
		this.orgjed = orgjed;
	}

	public Komitent getKomitent() {
		return komitent;
	}

	public void setKomitent(Komitent komitent) {
		this.komitent = komitent;
	}

	public Integer getMesec() {
		return mesec;
	}

	public void setMesec(Integer mesec) {
		this.mesec = mesec;
	}

	
	
}
