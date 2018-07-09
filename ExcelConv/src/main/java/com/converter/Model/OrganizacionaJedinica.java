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
@Table(name="orgjed")
@NamedQuery(name="OrganizacionaJedinica.findAll", query="SELECT n FROM OrganizacionaJedinica n")
public class OrganizacionaJedinica implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name="sifra")
	private Integer sifra;
	
	@Column(name="naziv")
	private String naziv;
	
	@Column(name="pib")
	private String pib;

	@Column(name="adresa")
	private String adresa;
	
	@ManyToOne
	private Mesto mesto;

	public OrganizacionaJedinica() {}

	
	public OrganizacionaJedinica(Integer id, Integer sifra, String naziv, String pib, String adresa, Mesto mesto) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.pib = pib;
		this.adresa = adresa;
		this.mesto = mesto;
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

	public String getPib() {
		return pib;
	}

	public void setPib(String pib) {
		this.pib = pib;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Mesto getMesto() {
		return mesto;
	}

	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}
	
	
	
	
}
