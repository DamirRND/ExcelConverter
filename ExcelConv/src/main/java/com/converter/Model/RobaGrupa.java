package com.converter.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="robagrupa")
@NamedQuery(name="RobaGrupa.findAll", query="SELECT rg FROM RobaGrupa rg")
public class RobaGrupa implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name="sifra")
	private Integer sifra;
	
	@Column(name="naziv")
	private String naziv;

	public RobaGrupa() {}

	public RobaGrupa(Integer id, Integer sifra, String naziv) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
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
	
	
}
