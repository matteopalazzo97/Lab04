package it.polito.tdp.lab04.model;

public class Corso implements Comparable<Corso>{
	
	private String codIns;
	private int crediti;
	private String nome;
	private int pd;
	
	//costruttore
	public Corso(String codIns, int crediti, String nome, int pd) {
		super();
		this.codIns = codIns;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}
	
	//getters e setters
	public String getCodIns() {
		return codIns;
	}
	public void setCodIns(String codIns) {
		this.codIns = codIns;
	}
	public int getCrediti() {
		return crediti;
	}
	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getPd() {
		return pd;
	}
	public void setPd(int pd) {
		this.pd = pd;
	}
	
	//tostring
	@Override
	public String toString() {
		return nome;
	}
	
	//hashcode e equals per la chiave primaria
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codIns == null) ? 0 : codIns.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codIns == null) {
			if (other.codIns != null)
				return false;
		} else if (!codIns.equals(other.codIns))
			return false;
		return true;
	}

	@Override
	public int compareTo(Corso o) {
		
		return this.nome.compareTo(o.nome);
	}
	
	

}
