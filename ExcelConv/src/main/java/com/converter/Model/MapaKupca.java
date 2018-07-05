package com.converter.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MapaKupca {

	
	@Id
	@Column(name="vp_id")
	private int vpid;
	private int kupacsifraext;
	private String kupacnazivext;
	private int kupac_id;
	public int getVpid() {
		return vpid;
	}
	public void setVpid(int vpid) {
		this.vpid = vpid;
	}
	public int getKupacsifraext() {
		return kupacsifraext;
	}
	public void setKupacsifraext(int kupacsifraext) {
		this.kupacsifraext = kupacsifraext;
	}
	public String getKupacnazivext() {
		return kupacnazivext;
	}
	public void setKupacnazivext(String kupacnazivext) {
		this.kupacnazivext = kupacnazivext;
	}
	public int getKupac_id() {
		return kupac_id;
	}
	public void setKupac_id(int kupac_id) {
		this.kupac_id = kupac_id;
	}
}
