package it.polito.tdp.alien.model;

public class Word {
	
	private String parola;
	private String traduzione;
	
	public Word(String parola, String traduzione) {
		this.parola = parola;
		this.traduzione = traduzione;
	}

	/**
	 * @return the parola
	 */
	public String getParola() {
		return parola;
	}

	/**
	 * @return the traduzione
	 */
	public String getTraduzione() {
		return traduzione;
	}

	/**
	 * @param traduzione the traduzione to set
	 */
	public void setTraduzione(String traduzione) {
		this.traduzione = traduzione;
	}

	public boolean equals(Object obj) {
		if(obj instanceof Word && ((Word) obj).getParola().compareTo(this.parola)==0)
			return true;
		return false;
	}
	
}
