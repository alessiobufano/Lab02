package it.polito.tdp.alien.model;

import java.util.*;

public class AlienDictionary {

	private List<WordEnhanced> dizionario = new LinkedList<>();

	public List<WordEnhanced> getDizionario() {
		return dizionario;
	}

	public void addWord(String p, String t) {
		p.toLowerCase();
		t.toLowerCase();
		WordEnhanced w = new WordEnhanced(p);
		WordEnhanced g = this.translateWord(p); 
		if(g==null)
		{
			this.dizionario.add(w);	
			w.setTraduzioni(t);
		}
		else
			g.setTraduzioni(t);
	}
	
	public WordEnhanced translateWord(String p) {
		p.toLowerCase();
		for (WordEnhanced w : this.dizionario)
			if(w.getParola().compareTo(p)==0)
				return w;
		return null;
	}
	
	public WordEnhanced translateWordWild(String alienWord) {
		alienWord = alienWord.substring(0, 1).toUpperCase()+alienWord.substring(1, alienWord.length()).toLowerCase();
		int index = alienWord.indexOf("?");
		String a1 = alienWord.substring(0, index);
		String a2 = alienWord.substring(index+1, alienWord.length());
		String s = null;
		String s1 = null;
		String s2 = null;
		int trovato = 0;
		WordEnhanced result = null;
		for(WordEnhanced w : this.dizionario) {
			s = w.getParola();
			s1 = s.substring(0, index);
			s2 = s.substring(index+1, s.length());
			if(s1.equals(a1) && s2.equals(a2)) {
				trovato++;
				result = w;
			}
		}
		if(trovato == 0 || trovato > 1)
			return null;
		else
			return result;
	}
	
	public void reset() {
		this.dizionario.clear();
	}
	
}
