package com.converter.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="nalogst")
@NamedQuery(name="NalogStavka.findAll", query="SELECT n FROM NalogStavka n")
public class NalogStavka implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private Komitent kupac;
	
	@ManyToOne
	private Roba roba;
	
	@Column(name="cena")
	private Double cena;
	
	@Column(name="kolicina")
	private Double kolicina;
	
	@Column(name="iznos")
	private Double iznos;
	
	@ManyToOne
	private Nalog nalog;
	
	@Column(name="robasifraext", nullable=true)
	private Integer robaSifraExt;
	
	@Column(name="robanazivext")
	private String robaNazivExt;
	
	@Column(name="kupacsifraext", nullable=true)
	private Integer kupacSifraExt;
	
	@Column(name="kupacnazivext")
	private String kupacNazivExt;

	public NalogStavka() {}

	
	public NalogStavka(Integer id, Komitent kupac, Roba roba, Double cena, Double kolicina, Double iznos, Nalog nalog,
			Integer robaSifraExt, String robaNazivExt, Integer kupacSifraExt, String kupacNazivExt) {
		super();
		this.id = id;
		this.kupac = kupac;
		this.roba = roba;
		this.cena = cena;
		this.kolicina = kolicina;
		this.iznos = iznos;
		this.nalog = nalog;
		this.robaSifraExt = robaSifraExt;
		this.robaNazivExt = robaNazivExt;
		this.kupacSifraExt = kupacSifraExt;
		this.kupacNazivExt = kupacNazivExt;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Komitent getKupac() {
		return kupac;
	}

	public void setKupac(Komitent kupac) {
		this.kupac = kupac;
	}

	public Roba getRoba() {
		return roba;
	}

	public void setRoba(Roba roba) {
		this.roba = roba;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Double getKolicina() {
		return kolicina;
	}

	public void setKolicina(Double kolicina) {
		this.kolicina = kolicina;
	}

	public Double getIznos() {
		return iznos;
	}

	public void setIznos(Double iznos) {
		this.iznos = iznos;
	}

	public Nalog getNalog() {
		return nalog;
	}

	public void setNalog(Nalog nalog) {
		this.nalog = nalog;
	}

	public Integer getRobaSifraExt() {
		return robaSifraExt;
	}

	public void setRobaSifraExt(Integer robaSifraExt) {
		this.robaSifraExt = robaSifraExt;
	}

	public String getRobaNazivExt() {
		return robaNazivExt;
	}

	public void setRobaNazivExt(String robaNazivExt) {
		this.robaNazivExt = robaNazivExt;
	}

	public Integer getKupacSifraExt() {
		return kupacSifraExt;
	}

	public void setKupacSifraExt(Integer kupacSifraExt) {
		this.kupacSifraExt = kupacSifraExt;
	}

	public String getKupacNazivExt() {
		return kupacNazivExt;
	}

	public void setKupacNazivExt(String kupacNazivExt) {
		this.kupacNazivExt = kupacNazivExt;
	}
	

}
