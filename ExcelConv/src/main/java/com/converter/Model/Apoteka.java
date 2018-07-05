package com.converter.Model;

public class Apoteka {

	
	private int sifraApoteke;
	private String nazivApoteke;
	private int sifraRobe;
	private String nazivRobe;
	private int kolicina;
	private double vrijednost;
	public int getSifraApoteke() {
		return sifraApoteke;
	}
	public void setSifraApoteke(int sifraApoteke) {
		this.sifraApoteke = sifraApoteke;
	}
	public String getNazivApoteke() {
		return nazivApoteke;
	}
	public void setNazivApoteke(String nazivApoteke) {
		this.nazivApoteke = nazivApoteke;
	}
	public int getSifraRobe() {
		return sifraRobe;
	}
	public void setSifraRobe(int sifraRobe) {
		this.sifraRobe = sifraRobe;
	}
	public String getNazivRobe() {
		return nazivRobe;
	}
	public void setNazivRobe(String nazivRobe) {
		this.nazivRobe = nazivRobe;
	}
	public int getKolicina() {
		return kolicina;
	}
	public void setKolicina(int kolicina) {
		this.kolicina = kolicina;
	}
	public double getVrijednost() {
		return vrijednost;
	}
	public void setVrijednost(double vrijednost) {
		this.vrijednost = vrijednost;
	}
	public Apoteka(int sifraApoteke, String nazivApoteke, int sifraRobe, String nazivRobe, int kolicina,
			float vrijednost) {
		super();
		this.sifraApoteke = sifraApoteke;
		this.nazivApoteke = nazivApoteke;
		this.sifraRobe = sifraRobe;
		this.nazivRobe = nazivRobe;
		this.kolicina = kolicina;
		this.vrijednost = vrijednost;
	}
	
	public Apoteka()
	{
		
	}
	
	
}
