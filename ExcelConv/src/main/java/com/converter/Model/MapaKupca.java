package com.converter.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MapaKupca {

	
	@Id
	@Column(name="vp_id")
	private Integer vpid;
	
	
	
	
	@Column(name="kupacsifraext")
	private Integer kupacSifraExt;
	
	@Column(name="kupacnazivext")
	private String kupacNazivExt;
	
	@Column(name="kupac_id")
	private Integer kupacId;
	
}
