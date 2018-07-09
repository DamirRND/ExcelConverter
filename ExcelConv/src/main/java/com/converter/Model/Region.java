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
@Table(name="region")
@NamedQuery(name="Region.findAll", query="SELECT r FROM Region r")
public class Region implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name="sifra")
	private Integer sifra;
	
	@Column(name="naziv")
	private String naziv;
	
	
	@ManyToOne
	private Entitet entitet;

	
	public Region() {}


	public Region(Integer id, Integer sifra, String naziv, Entitet entitet) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.entitet = entitet;
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


	public Entitet getEntitet() {
		return entitet;
	}


	public void setEntitet(Entitet entitet) {
		this.entitet = entitet;
	}

	
	
}
