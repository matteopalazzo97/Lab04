package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	/*
	 * metodo per ottenere nome e cognome di uno studente data la matricola
	 * è molto stupido, ma fondamentalmente è il primo metodo di un DAO che faccio da solo
	 * quindi mi devo aiutare con la soluzione loro 
	 * 
	 * in ogni caso le parti che sono sempre uguali tipo le connessioni al db col try catch 
	 * non necessariamente devo ricordarmi a memoria come si fanno, tanto all'esame posso imporatrmi tutto quello
	 * che voglio e copiarmele per come mi servono
	 */
	
	public Studente getStudente (int matricola) throws SQLException {
		
		Studente s = null;
		
		final String sql = "SELECT * FROM studente where matricola = ?";
		
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery(); //le parentesi devono essere vuote, se ci metti sql dentro lui legge ?
											  //e per lui non ha senso e fa 484758 errori ed eccezioni
			
			//io stavo copiando il corsoDAO gettutticorsi che importava una lista di corsi, ma qua devo importare
			//uno studente quindi mi basta un if
			
			if(rs.next()) {
				s = new Studente(matricola, rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
			}
			
			conn.close();
			
			return s;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("Errore connessione DB");
		}
		
	}
	
	public List<Corso> getCorsiStudente (Integer matricola){
		
		List<Corso> corsiStudente = new LinkedList<Corso>();
		
		Corso c = null;
		
		final String sql = "SELECT c.`codins`, c.`crediti`, c.`nome`, c.`pd` "
				+ "FROM iscrizione i, corso c "
				+ "WHERE i.`codins` = c.`codins` "
				+ "AND i.`matricola` = ?";
		
		
		try {
			
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				
				c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				corsiStudente.add(c);
				
			}
			
			conn.close();
			
		} catch (SQLException e) {
			System.out.println("Errore DB");
		}
				
		return corsiStudente;		
				
	}

}
