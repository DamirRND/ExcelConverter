package com.converter.Model;

import java.io.Serializable;
import java.sql.Date;

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
@Table(name="nalog")
@NamedQuery(name="Nalog.findAll", query="SELECT n FROM Nalog n")
public class Nalog implements Serializable{

	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="status")
	private int status;
	
	@Column(name="sifra")
	private int sifra;
	
	
	@Column(name="datum")
	private Date datum;
	
	
	@Column(name="izvornifajl")
	private String izvornifajl;
	
	@Column(name="napomena")
	private String napomena;


	@ManyToOne
	private OrganizacionaJedinica orgjed;
	
	@ManyToOne
	private Komitent komitent;
	
	@Column(name="mesec")
	private int mesec;

	public Nalog() {}
	
	public Nalog(int id, int status, int sifra, Date datum, String izvornifajl, String napomena,
			OrganizacionaJedinica orgjed, Komitent komitent, int mesec) {
		super();
		this.id = id;
		this.status = status;
		this.sifra = sifra;
		this.datum = datum;
		this.izvornifajl = izvornifajl;
		this.napomena = napomena;
		this.orgjed = orgjed;
		this.komitent = komitent;
		this.mesec = mesec;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getSifra() {
		return sifra;
	}

	public void setSifra(int sifra) {
		this.sifra = sifra;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getIzvornifajl() {
		return izvornifajl;
	}

	public void setIzvornifajl(String izvornifajl) {
		this.izvornifajl = izvornifajl;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public OrganizacionaJedinica getOrgjed() {
		return orgjed;
	}

	public void setOrgjed(OrganizacionaJedinica orgjed) {
		this.orgjed = orgjed;
	}

	public Komitent getKomitent() {
		return komitent;
	}

	public void setKomitent(Komitent komitent) {
		this.komitent = komitent;
	}

	public int getMesec() {
		return mesec;
	}

	public void setMesec(int mesec) {
		this.mesec = mesec;
	}
	
	
	
	
}
