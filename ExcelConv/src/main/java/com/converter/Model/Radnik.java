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
@Table(name="korisnikapp")
@NamedQuery(name="Radnik.findAll", query="SELECT r FROM Radnik r")
public class Radnik implements Serializable{
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="korime")
	private String korime;
	
	@Column(name="korlozinka")
	private String korlozinka;
	
	@Column(name="naziv")
	private String naziv;
	
	
	@ManyToOne
	private KorGrupa korgrupa;


	public Radnik() {}
	
	public Radnik(int id, String korime, String korlozinka, String naziv, KorGrupa korgrupa) {
		super();
		this.id = id;
		this.korime = korime;
		this.korlozinka = korlozinka;
		this.naziv = naziv;
		this.korgrupa = korgrupa;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getKorime() {
		return korime;
	}


	public void setKorime(String korime) {
		this.korime = korime;
	}


	public String getKorlozinka() {
		return korlozinka;
	}


	public void setKorlozinka(String korlozinka) {
		this.korlozinka = korlozinka;
	}


	public String getNaziv() {
		return naziv;
	}


	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}


	public KorGrupa getKorgrupa() {
		return korgrupa;
	}


	public void setKorgrupa(KorGrupa korgrupa) {
		this.korgrupa = korgrupa;
	}

	public boolean isEmpty() {
		return false;
	}
	
	
	
	

}
