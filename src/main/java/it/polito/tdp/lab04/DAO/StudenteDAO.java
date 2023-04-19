package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	
	/**
	 * Data una matricola, recupera le informazioni riguardo lo studente
	 * @param matricola
	 * @return
	 * @throws SQLException 
	 */
	public Studente getStudente(int matricola) throws SQLException {
		String sql = "SELECT * FROM studente where matricola=?";
		Studente studente = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {
				studente = new Studente(matricola, rs.getString("cognome"), rs.getString("nome"), rs.getString("cds"));
			}

			conn.close();
			return studente;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Errore di connessione al DB");
		}
	}
	
	
	/**
	 * Funzione che restituisce tutti i corsi ai quali è iscritto uno studente
	 * @param studente
	 * @return
	 * @throws SQLException 
	 */
	public List<Corso> getCorsiFromStudente(Studente studente) throws SQLException {
		String sql = "SELECT * FROM iscrizione, corso WHERE iscrizione.codins=corso.codins AND matricola=?";
		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso corso = new Corso(rs.getString("codins"), rs.getString("nome"), rs.getInt("crediti"), rs.getInt("pd"));
				corsi.add(corso);
			}
			Collections.sort(corsi); 
			conn.close();
			return corsi;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("Errore di connessione al DB");
		}
	}
	

}
