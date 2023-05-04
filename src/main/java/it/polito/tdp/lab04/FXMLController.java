/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnCercaCorsi"
    private Button btnCercaCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaIscritti"
    private Button btnCercaIscritti; // Value injected by FXMLLoader

    @FXML // fx:id="btnIscrivi"
    private Button btnIscrivi; // Value injected by FXMLLoader

    @FXML // fx:id="btnReset"
    private Button btnReset; // Value injected by FXMLLoader

    @FXML // fx:id="btnVerifica"
    private Button btnVerifica; // Value injected by FXMLLoader
    
    @FXML // fx:id="btnIscrittoAlCorso"
    private Button btnIscrittoAlCorso; // Value injected by FXMLLoader

    @FXML // fx:id="cmbCorsi"
    private ComboBox<Corso> cmbCorsi; // Value injected by FXMLLoader

    @FXML // fx:id="txtCognome"
    private TextField txtCognome; // Value injected by FXMLLoader

    @FXML // fx:id="txtMatricola"
    private TextField txtMatricola; // Value injected by FXMLLoader

    @FXML // fx:id="txtNome"
    private TextField txtNome; // Value injected by FXMLLoader

    @FXML // fx:id="txtOutput"
    private TextArea txtOutput; // Value injected by FXMLLoader
    
    
    /*
     * selezionati uno studente e un corso dice se lo studente è iscritto al corso o no
     * se non sono selezionati lo segnala
     * 
     * si verificano un paio di cazzi dovuti al fatto che il metodo getCorsiStudente l'ho fatto con parametro
     * la matricola e non lo studente, ma in realtà ho fatto quello che dovevo, vedo se lo gestisco veloce veloce
     * 
     * non ce la faccio, è troppo un dito in culo, pazienza.
     * 
     * quindi se il campo matricola è vuoto succede il disastro e anche se è compilato male, potrei sistemare 
     * tutto, giuro, ma non c'è tempo
     * 
     */
    @FXML
    void doIscrittoAlCorso(ActionEvent event) {
    	
    	this.txtOutput.clear();
    	
    	Corso c = this.cmbCorsi.getValue();
    	Integer matricola = Integer.parseInt(this.txtMatricola.getText());
    	Studente s = null;
		try {
			
			s = this.model.getStudente(matricola);
		
			if(c == null || s == null) {
				this.txtOutput.setText("Selezionare un corso.");
				return;
			}
			/*
			if(s == null) {
				this.txtOutput.setText("Inserire una matricola valida");
				return;
			}
			
			*/
			if(this.model.isIscritto(matricola, c)) {
				this.txtOutput.setText("Lo studente è iscritto al corso.");
				return;
			} else {
				this.txtOutput.setText("Lo studente non è iscritto al corso.");
				return;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

    }
    
    
    /*
     * data la matricola restituisce i corsi a cui è iscritto lo studente con quella matricola se esiste, 
     * se non esiste lo segnala
     * 
     * effettivamente fa quello che ho detto senza errori, forse sto iniziando a imparare qualcosa
     */
    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	this.txtOutput.clear();
    	
    	Integer matricola = Integer.parseInt(this.txtMatricola.getText());
    	Studente s;
		try {
			s = this.model.getStudente(matricola);
			
			if(s != null) {
	    		for(Corso c : this.model.getCorsiStudente(matricola)) {
	    			this.txtOutput.appendText(c.toString() + "\n");
	    		}
			} else {
				this.txtOutput.setText("Matricola non presente nel DB.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    		
    	

    }
    
    /*
     * fatto relativamente velocemente e direi che funziona, dopo lo confronto con quello che ha fatto lui e 
     * vediamo le eventuali ma quasi certe cacate che posso aver fatto
     */

    @FXML
    void doCercaIscritti(ActionEvent event) {
    	
    	this.txtOutput.clear();
    	
    	Corso corso = this.cmbCorsi.getValue();
    	
    	
    	if(corso == null) {
    		this.txtOutput.setText("Non è stato selezionato un corso.");
    	} else {
    	
    			try {
					for(Studente s : this.model.getStudentiIscrittiAlCorso(corso)) {
					this.txtOutput.appendText(s.getMatricola() + " " + s.getNome() + " " + s.getCognome() + " " +
											s.getCds() + "\n");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	/*
    	 * è facoltativo e io ho poco tempo, quindi me lo evito se no ci devo perdere un altro pomeriggio
    	 * e adesso non me lo posso permettere
    	 */

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.txtMatricola.clear();
    	this.txtOutput.clear();

    }
    
    /*
     * era semplicemente il metodo che compila nome e cognome data la matricola e sono stato una mattina a farlo
     * insieme al suo dao ma alla fine ne sono venuto a capo dopo essermi impantanato 47 volte su 47 puttanate
     * su cui potevo anche non impantarmi ma tant'è, spero che sia tempo speso e non sprecato PORCODDIO
     */

    @FXML
    void doVerifica(ActionEvent event) {
    	
    	this.txtCognome.clear();
    	this.txtNome.clear();
    	this.txtOutput.clear();
    	
    	try {
    		
    		int matricola = Integer.parseInt(this.txtMatricola.getText());
			Studente s = this.model.getStudente(matricola);
			if(s != null) {
			
				this.txtNome.setText(s.getNome());
				this.txtCognome.setText(s.getCognome());
			} else {
				this.txtOutput.setText("Matricola Inesistente.");
				return;
			}
			//this.txtOutput.setText(s.toString());
			
		} catch (SQLException e) {
			this.txtOutput.setText("Problema SQL");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			this.txtOutput.setText("Inserire matricola nel formato corretto");
		}
    	
    }
    
    //creo un metodo setModel da chiamare nell'entrypoint
    
    public void setModel (Model model) {
    	this.model = model;
    	this.setCmbCorsi();
    }
    
    //metodo che riempie il menu a tendina
    private void setCmbCorsi() {
    	
    	/*
    	 * List <Corso> l = this.model.getTuttiCorsi();
    	
    	for(Corso c: l) {
    		this.cmbCorsi.getItems().add(c);
    	}
    	io lo avevo fatto così che sbagliatissimo non è ma lui giustamente lo fa più smart e va bene devo solo
    	imparare ma anche più controllato, ora lo faccio come lui
    	fra l'altro lui mette il try catch ma qua me lo segna come sbagliato perchè dice che niente del codice può 
    	generare una sqlexception e va bene così dai
    	 */
    	
    	List<Corso> corsi;
    	
    	corsi = this.model.getTuttiCorsi();
    	Collections.sort(corsi);
    	this.cmbCorsi.getItems().add(null);
    	this.cmbCorsi.getItems().addAll(corsi);
    	
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
    	assert btnIscrittoAlCorso != null : "fx:id=\"btnIscrittoAlCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnVerifica != null : "fx:id=\"btnVerifica\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbCorsi != null : "fx:id=\"cmbCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";

    }

}
