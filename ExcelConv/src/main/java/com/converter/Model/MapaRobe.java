package com.converter.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MapaRobe {

	
	@Id
	@Column(name="vp_id")
	private Integer vpid;
	
	
	
	
	@Column(name="robasifraext")
	private Integer robaSifraExt;
	
	@Column(name="robanazivext")
	private String robaNazivExt;
	
	@Column(name="roba_id")
	private Integer robaId;

	public MapaRobe() {}
	public MapaRobe(Integer vpid, Integer robaSifraExt, String robaNazivExt, Integer robaId) {
		super();
		this.vpid = vpid;
		this.robaSifraExt = robaSifraExt;
		this.robaNazivExt = robaNazivExt;
		this.robaId = robaId;
	}

	public Integer getVpid() {
		return vpid;
	}

	public void setVpid(Integer vpid) {
		this.vpid = vpid;
	}

	public Integer getRobaSifraExt() {
		return robaSifraExt;
	}

	public void setRobaSifraExt(Integer robaSifraExt) {
		this.robaSifraExt = robaSifraExt;
	}

	public String getRobaNazivExt() {
		return robaNazivExt;
	}

	public void setRobaNazivExt(String robaNazivExt) {
		this.robaNazivExt = robaNazivExt;
	}

	public Integer getRobaId() {
		return robaId;
	}

	public void setRobaId(Integer robaId) {
		this.robaId = robaId;
	}
	
	 
	
	
}
