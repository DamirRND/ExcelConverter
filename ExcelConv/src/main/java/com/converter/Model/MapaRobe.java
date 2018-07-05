package com.converter.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MapaRobe {

	
	@Id
	@Column(name="vp_id")
	private int vpid;
	private int robasifraext;
	private String robanazivext;
	private int roba_id;
	public int getVp_id() {
		return vpid;
	}
	public void setVp_id(int vpid) {
		this.vpid = vpid;
	}
	public int getRobasifraext() {
		return robasifraext;
	}
	public void setRobasifraext(int robasifraext) {
		this.robasifraext = robasifraext;
	}
	public String getRobanazivext() {
		return robanazivext;
	}
	public void setRobanazivext(String robanazivext) {
		this.robanazivext = robanazivext;
	}
	public int getRoba_id() {
		return roba_id;
	}
	public void setRoba_id(int roba_id) {
		this.roba_id = roba_id;
	}
	
	
	
}
