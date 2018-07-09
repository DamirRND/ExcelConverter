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
	private Integer id;
	
	@Column(name="korime")
	private String korIme;
	
	@Column(name="korlozinka")
	private String korLozinka;
	
	@Column(name="naziv")
	private String naziv;
	
	
	@ManyToOne
	private KorGrupa korgrupa;


	public Radnik() {}


	
	public Radnik(Integer id, String korIme, String korLozinka, String naziv, KorGrupa korgrupa) {
		super();
		this.id = id;
		this.korIme = korIme;
		this.korLozinka = korLozinka;
		this.naziv = naziv;
		this.korgrupa = korgrupa;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getKorIme() {
		return korIme;
	}


	public void setKorIme(String korIme) {
		this.korIme = korIme;
	}


	public String getKorLozinka() {
		return korLozinka;
	}


	public void setKorLozinka(String korLozinka) {
		this.korLozinka = korLozinka;
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

	
	

}
