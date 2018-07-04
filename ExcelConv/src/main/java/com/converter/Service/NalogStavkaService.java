package com.converter.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.converter.Model.NalogStavka;
import com.converter.Repository.NalogStavkaRepository;

@Service
public class NalogStavkaService {

	
	private final NalogStavkaRepository nlgrep;
	
	@Autowired
	public NalogStavkaService(NalogStavkaRepository nlgrep) {
		this.nlgrep = nlgrep;
	}
	
	
	public List<NalogStavka> lista;
	
	public List<NalogStavka> findSveCustom(int id, int sifra){
		setLista(nlgrep.findAllByKupacIdAndKupacsifraext(id, sifra));
		return (List<NalogStavka>) nlgrep.findAllByKupacIdAndKupacsifraext(id, sifra);
	}

	public List<NalogStavka> getLista() {
		return lista;
	}

	public void setLista(List<NalogStavka> lista) {
		this.lista = lista;
	}
	
	
}
