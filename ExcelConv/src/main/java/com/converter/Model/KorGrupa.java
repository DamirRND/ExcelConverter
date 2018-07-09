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
@Table(name="korgrupa")
@NamedQuery(name="KorGrupa.findAll", query="SELECT k FROM KorGrupa k")
public class KorGrupa implements Serializable{

	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@Column(name="sifra")
	private Integer sifra;
	
	@Column(name="opis")
	private String opis;
	
	@Column(name="nivo")
	private int nivo;

	
	public KorGrupa() {}


	public KorGrupa(Integer id, Integer sifra, String opis, int nivo) {
		super();
		this.id = id;
		this.sifra = sifra;
		this.opis = opis;
		this.nivo = nivo;
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


	public String getOpis() {
		return opis;
	}


	public void setOpis(String opis) {
		this.opis = opis;
	}


	public int getNivo() {
		return nivo;
	}


	public void setNivo(int nivo) {
		this.nivo = nivo;
	}
	
	
	
}
