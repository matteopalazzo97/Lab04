package it.polito.tdp.lab04.model;

public class Studente {
	
	private int matricola;	//matricola dello studente
	private String cognome;	// cognome dello studente
	private String nome;	//nome dello studente
	private String cds;		//corso di studi dello studente
	
	
	/**
	 * Costruttore dalla matricola
	 * @param matricola
	 */
	public Studente(int matricola) {
		super();
		this.matricola = matricola;
	}

	/**
	 * Costruttore con tutti gli argomenti
	 * @param matricola
	 * @param cognome
	 * @param nome
	 * @param cds
	 */
	public Studente(int matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.cds = cds;
	}

	
	//GETTERS AND SETTERS
	
	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCds() {
		return cds;
	}

	public void setCds(String cds) {
		this.cds = cds;
	}

	
	@Override
	public String toString() {
		return cognome + " " + nome;
	}
	

}
