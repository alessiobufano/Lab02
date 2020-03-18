package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;

import it.polito.tdp.alien.model.AlienDictionary;
import it.polito.tdp.alien.model.WordEnhanced;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private AlienDictionary model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtTradurre;

    @FXML
    private Button btmTraduci;

    @FXML
    private TextArea txtTradotto;

    @FXML
    private Button btmClear;

    @FXML
    void doReset(ActionEvent event) {
    	txtTradurre.clear();
    	txtTradotto.clear();
    	this.model.reset();
    }

    @FXML
    void doTranslate(ActionEvent event) {
    	boolean nuova = false;
    	boolean wild = false;
    	String text = txtTradurre.getText();
    	txtTradotto.clear();
    	
    	//Controllo errori input
    	if(text.length() == 0) 
    	{
    		txtTradotto.appendText("Inserire almeno un parametro\n");
    		return;
    	}
    
    	String pattern = "[A-Za-z ?]*";
    	if(!text.matches(pattern)) 
    	{
    		txtTradotto.appendText("Parametro non valido, gli unici caratteri ammessi sono [A-Za-z ?]\n");
    		return;
    	}
    	
    	String array[] = text.split(" ");
    	
    	if(text.indexOf("?") != text.lastIndexOf("?")) {
    		txtTradotto.appendText("Input non valido, è ammesso un solo ?\n");
    		return;
    	}
    	
    	if(text.contains("?"))
    		wild = true;
    	
    	if(wild && !array[0].contains("?")) {
    		txtTradotto.appendText("Input non valido, ? ammesso solo nell'alienWord\n");
    		return;
    	}
    	
    	if(array.length>1) 
    		nuova = true;
    	
    	if(nuova && wild) {
    		txtTradotto.appendText("Input non valido, impossibile aggiungere elementi contenenti ?\n");
    		return;
    	}
    	
    	//Fine controllo
    	
    	String alienWord = array[0];
    	
    	if(nuova) 
    	{
    		try {
    			for(int i=1; i<array.length; i++)
    				this.model.addWord(alienWord, array[i]);
    			txtTradotto.appendText("Nuovo elemento aggiunto al dizionario\n");
    		} catch(IllegalStateException ise) {
    			txtTradotto.appendText(ise.getMessage());
    		}
    	}
    	else
    	{
    		if(!wild)
    		{
    			WordEnhanced result = this.model.translateWord(alienWord);
        		if(result != null)
        			txtTradotto.appendText(result.toString()+"\n");
        		else
        			txtTradotto.appendText("Parola non ancora presente nel dizionario\n");	
    		}
    		else
    		{
    			WordEnhanced result = this.model.translateWordWild(alienWord);
        		if(result != null)
        			txtTradotto.appendText(result.toString()+"\n");
        		else
        			txtTradotto.appendText("Parola non ancora presente nel dizionario oppure più parole aliene potrebbero corrispondere ad essa\n");	
    		}
    	}
    	
    }

    @FXML
    void initialize() {
        assert txtTradurre != null : "fx:id=\"txtTradurre\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btmTraduci != null : "fx:id=\"btmTraduci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTradotto != null : "fx:id=\"txtTradotto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btmClear != null : "fx:id=\"btmClear\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel (AlienDictionary model) {
    	this.model = model;
    }
    
}
