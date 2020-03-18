package it.polito.tdp.alien.model;

import java.util.*;

public class AlienDictionary {

	private List<Word> dizionario = new LinkedList<>();

	public List<Word> getDizionario() {
		return dizionario;
	}

	public void addWord(String p, String t) {
		p.toLowerCase();
		t.toLowerCase();
		Word w = new Word(p, t);
		Word g = this.translateWord(p); 
		if(g==null)
			this.dizionario.add(w);
		else
		{
			if(g.getTraduzione().compareTo(t)!=0)
				g.setTraduzione(t);
			else
				throw new IllegalStateException("Errore! Elemento già presente");
		}
	}
	
	public Word translateWord(String p) {
		p.toLowerCase();
		for (Word w : this.dizionario)
			if(w.getParola().compareTo(p)==0)
				return w;
		return null;
	}
	
	public void reset() {
		this.dizionario.clear();
	}
	
	
}
