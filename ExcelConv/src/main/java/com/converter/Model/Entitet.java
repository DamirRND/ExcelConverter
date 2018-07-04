package com.converter.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="entitet")
@NamedQuery(name="Entitet.findAll", query="SELECT e FROM Entitet e")
public class Entitet implements Serializable{
	
	
	@Id
	@Column(name="id")
	private int id;
	
	@Column(name="sifra")
	private int sifra;
	
	@Column(name="naziv")
	private String naziv;

	
	public Entitet() {}
	
	public Entitet(int id, int sifra, String naziv) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.naziv = naziv;
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
	
	
	
	

}
