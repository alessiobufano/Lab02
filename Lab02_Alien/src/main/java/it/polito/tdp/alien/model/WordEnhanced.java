package it.polito.tdp.alien.model;

import java.util.*;

public class WordEnhanced {
	
	private String parola;
	private List<String> traduzioni;
	
	public WordEnhanced(String parola) {
		this.parola = parola;
		this.traduzioni = new LinkedList<>();
	}

	public String getParola() {
		return parola;
	}

	public List<String> getTraduzioni() {
		return traduzioni;
	}

	public void setTraduzioni(String traduzione) {
		boolean b = false;
		for(String s : this.traduzioni)
			if(s.equals(traduzione))
				b = true;
		if(b==false)
			this.traduzioni.add(traduzione);
		else
			throw new IllegalStateException("Errore! Elemento già presente");
	}

	public boolean equals(Object obj) {
		if(obj instanceof WordEnhanced && ((WordEnhanced) obj).getParola().compareTo(this.parola)==0)
			return true;
		return false;
	}
	
	public String toString() {
		String s = "Traduzioni di "+this.parola+": ";
		for(String t : this.traduzioni)
			s += t+", ";
		s = s.substring(0, s.length()-2)+".";
		return s;
	}
	
}
