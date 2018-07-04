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
@Table(name="komitent")
@NamedQuery(name="Komitent.findAll", query="SELECT k FROM Komitent k")
public class Komitent implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	
	@Column(name="sifra")
	private int sifra;
	
	@Column(name="naziv")
	private String naziv;

	@Column(name="pib")
	private String pib;
	
	
	@Column(name="adresa")
	private String adresa;
	
	@Column(name="tip")
	private String tip;
	
	@ManyToOne
	private Mesto mesto;
	
	
	@ManyToOne
	private Ustanova ustanova;

	public Komitent() {}
	
	public Komitent(int id, int sifra, String naziv, String pib, String adresa, String tip, Mesto mesto,
			Ustanova ustanova) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
		this.pib = pib;
		this.adresa = adresa;
		this.tip = tip;
		this.mesto = mesto;
		this.ustanova = ustanova;
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


	public String getTip() {
		return tip;
	}


	public void setTip(String tip) {
		this.tip = tip;
	}


	public Mesto getMesto() {
		return mesto;
	}


	public void setMesto(Mesto mesto) {
		this.mesto = mesto;
	}


	public Ustanova getUstanova() {
		return ustanova;
	}


	public void setUstanova(Ustanova ustanova) {
		this.ustanova = ustanova;
	}
	
	
	
	
}
