package it.polito.tdp.lab04.DAO;

import java.sql.SQLException;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		try {
			cdao.getTuttiICorsi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("Errore nella connessione al DB");
		}
		
		
	}

}
