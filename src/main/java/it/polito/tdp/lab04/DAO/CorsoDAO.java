package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/**
	 * Funzione che legge tutti i corsi dal database
	 * @return una lista con tutti i corsi
	 * @throws SQLException 
	 */
	public List<Corso> getTuttiICorsi() throws SQLException {
		String sql = "SELECT * FROM corso";
		List<Corso> corsi = new ArrayList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");
				Corso corso = new Corso(codins, nome, numeroCrediti, periodoDidattico);
				corsi.add(corso);
			}
			
			conn.close();
			return corsi;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Errore di connessione al DB", e);
		}
	}
	
	/**
	 * Funzione che interroga il database per leggere tutti gli studenti iscritti ad un corso
	 * @param corso
	 * @return una lista con gli studenti iscritti
	 * @throws SQLException 
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) throws SQLException {
		String sql = "SELECT * FROM iscrizione, studente WHERE iscrizione.matricola=studente.matricola AND codins=?";
		List<Studente> iscritti = new ArrayList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				iscritti.add(new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds")));
			}

			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Errore di connessione al DB", e);
		}
		return iscritti;
	}

	
	
	/**
	 * Funzione che aggiunge uno studente all'elenco degli iscritti ad un corso
	 * @param studente
	 * @param corso
	 * @return
	 * @throws SQLException 
	 */
	public boolean iscriviStudenteACorso(Studente studente, Corso corso) throws SQLException {
		String sql = "INSERT IGNORE INTO `iscritticorsi`.`iscrizione` (`matricola`, `codins`) VALUES(?,?)";
		boolean returnValue = false;
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());
			
			int res = st.executeUpdate();	
			if (res == 1) {
				returnValue = true;
			}
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Errore di connessione al DB", e);
		}
		
		return returnValue;
	}
	
	

}
