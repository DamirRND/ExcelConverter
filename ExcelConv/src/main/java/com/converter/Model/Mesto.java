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
@Table(name="mesto")
@NamedQuery(name="Mesto.findAll", query="SELECT m FROM Mesto m")
public class Mesto implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="pttbroj")
	private int pttbroj;
	
	@Column(name="naziv")
	private String naziv;
	
	
	@ManyToOne
	private Region region;
	
	@ManyToOne
	private Entitet entitet;
	
	
	public Mesto() {}

	public Mesto(int id, int pttbroj, String naziv, Region region, Entitet entitet) {
		super();
		this.id = id;
		this.pttbroj = pttbroj;
		this.naziv = naziv;
		this.region = region;
		this.entitet = entitet;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPttbroj() {
		return pttbroj;
	}

	public void setPttbroj(int pttbroj) {
		this.pttbroj = pttbroj;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Entitet getEntitet() {
		return entitet;
	}

	public void setEntitet(Entitet entitet) {
		this.entitet = entitet;
	}
	
	

}
