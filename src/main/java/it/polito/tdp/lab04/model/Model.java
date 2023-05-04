package it.polito.tdp.lab04.model;

import java.sql.SQLException;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	//creo una istanza per ciascun dao, credo che sia il pattern dao a volerlo
	
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;
	
	//tonno, ricordati delle cose super base tipo dare un costruttore alle classi, se no perdi ore a capire cose
	//magari molto stupide
	
	public Model() {
		super();
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}



	public List<Corso> getTuttiCorsi(){
		return this.corsoDAO.getTuttiICorsi();
	}
	
	public Studente getStudente(Integer matricola) throws SQLException {
		return this.studenteDAO.getStudente(matricola);
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) throws SQLException {
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> getCorsiStudente(Integer matricola) throws SQLException {
		return this.studenteDAO.getCorsiStudente(matricola);
	}
	
	public boolean isIscritto (Integer matricola, Corso c) {
		return this.studenteDAO.getCorsiStudente(matricola).contains(c);
	}

}
