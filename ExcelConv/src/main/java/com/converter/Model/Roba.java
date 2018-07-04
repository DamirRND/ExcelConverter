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
@Table(name="roba")
@NamedQuery(name="Roba.findAll", query="SELECT r FROM Roba r")
public class Roba implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="sifra")
	private int sifra;
	
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="cena")
	private Double cena;
	
	
	@ManyToOne
	private RobaGrupa grupa;
	
	@Column(name="jm")
	private String jm;

	public Roba() {}
	
	public Roba(int id, int sifra, String naziv, Double cena, RobaGrupa grupa, String jm) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.cena = cena;
		this.grupa = grupa;
		this.jm = jm;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		if(Double.valueOf(cena) != null) {
			this.cena = cena;
		}else {
			double cijenaNova =0.0;
			this.cena = cijenaNova;
		}
		
	}

	public RobaGrupa getRobagrupa() {
		return grupa;
	}

	public void setRobagrupa(RobaGrupa grupa) {
		this.grupa = grupa;
	}

	public String getJm() {
		return jm;
	}

	public void setJm(String jm) {
		this.jm = jm;
	}
	
	
	
}
