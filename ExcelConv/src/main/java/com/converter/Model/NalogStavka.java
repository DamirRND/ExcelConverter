package com.converter.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private int id;
	
	@ManyToOne
	private Komitent kupac_id;
	
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
	
	@Column(name="robasifraext")
	private int robasifraext;
	
	@Column(name="robanazivext")
	private String robanazivext;
	
	@Column(name="kupacsifraext")
	private int kupacsifraext;
	
	@Column(name="kupacnazivext")
	private String kupacnazivext;

	public NalogStavka() {}
	
	public NalogStavka(int id, Komitent kupac_id, Roba roba, Double cena, Double kolicina, Double iznos, Nalog nalog,
			int robasifraext, String robanazivext, int kupacsifraext, String kupacnazivext) {
		super();
		this.id = id;
		this.kupac_id = kupac_id;
		this.roba = roba;
		this.cena = cena;
		this.kolicina = kolicina;
		this.iznos = iznos;
		this.nalog = nalog;
		this.robasifraext = robasifraext;
		this.robanazivext = robanazivext;
		this.kupacsifraext = kupacsifraext;
		this.kupacnazivext = kupacnazivext;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Komitent getKupac_id() {
		return kupac_id;
	}

	public void setKupac_id(Komitent kupac_id) {
		this.kupac_id = kupac_id;
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

	public int getRobasifraext() {
		return robasifraext;
	}

	public void setRobasifraext(int robasifraext) {
		this.robasifraext = robasifraext;
	}

	public String getRobanazivext() {
		return robanazivext;
	}

	public void setRobanazivext(String robanazivext) {
		this.robanazivext = robanazivext;
	}

	public int getKupacsifraext() {
		return kupacsifraext;
	}

	public void setKupacsifraext(int kupacsifraext) {
		this.kupacsifraext = kupacsifraext;
	}

	public String getKupacnazivext() {
		return kupacnazivext;
	}

	public void setKupacnazivext(String kupacnazivext) {
		this.kupacnazivext = kupacnazivext;
	}
	
	
	

}
