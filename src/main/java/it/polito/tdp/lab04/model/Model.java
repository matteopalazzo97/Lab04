package it.polito.tdp.lab04.model;

import java.sql.SQLException;
import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	
	public Model() {
		this.studenteDAO = new StudenteDAO();
		this.corsoDAO = new CorsoDAO();
	}
	
	
	/**
	 * Funzione che restituisce una lista con tutti i corsi nel database
	 * @return
	 * @throws SQLException 
	 */
	public List<Corso> getTuttiICorsi() throws SQLException{
		return this.corsoDAO.getTuttiICorsi();
	}
	
	
	/**
	 * Funzione che restituisce uno studente datala sua matricola
	 * @param matricola
	 * @return
	 * @throws SQLException 
	 */
	public Studente getStudente(int matricola) throws SQLException {
		return studenteDAO.getStudente(matricola);
	}
	
	
	/**
	 * Funzione che restituisce lelenco degli iscritti ad un corso
	 * @param corso
	 * @return
	 * @throws SQLException 
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) throws SQLException{
		return this.corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	
	/**
	 * Funzione che restituisce tutti i corsi ai quali è iscritto uno studente
	 * @param studente
	 * @return
	 * @throws SQLException 
	 */
	public List<Corso> getCorsiFromStudente(Studente studente) throws SQLException{
		return this.studenteDAO.getCorsiFromStudente(studente);
	}
	
	
	/**
	 * Funzione che dice se uno studente è iscritto ad un corso
	 * @param studente
	 * @param corso
	 * @return
	 * @throws SQLException 
	 */
	public boolean verificaIscrizione(Studente studente, Corso corso) throws SQLException {
		return getCorsiFromStudente(studente).contains(corso);
	}
	
	
	/**
	 * Funzione che iscrive uno studente ad un corso
	 * @param studente
	 * @param corso
	 * @return
	 * @throws SQLException 
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) throws SQLException {
		return this.corsoDAO.iscriviStudenteACorso(studente, corso);
	}
	
}
