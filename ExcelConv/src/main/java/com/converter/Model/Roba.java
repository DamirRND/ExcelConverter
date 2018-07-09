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
	private Integer id;
	
	@Column(name="sifra")
	private Integer sifra;
	
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="cena")
	private Double cena;
	
	
	@ManyToOne
	private RobaGrupa grupa;
	
	@Column(name="jm")
	private String jm;

	public Roba() {}
	
	

	public Roba(Integer id, Integer sifra, String naziv, Double cena, RobaGrupa grupa, String jm) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.cena = cena;
		this.grupa = grupa;
		this.jm = jm;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSifra() {
		return sifra;
	}

	public void setSifra(Integer sifra) {
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

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public RobaGrupa getGrupa() {
		return grupa;
	}

	public void setGrupa(RobaGrupa grupa) {
		this.grupa = grupa;
	}

	public String getJm() {
		return jm;
	}

	public void setJm(String jm) {
		this.jm = jm;
	}
	
	
	
	
}
