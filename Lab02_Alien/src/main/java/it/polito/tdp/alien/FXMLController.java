package it.polito.tdp.alien;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.*;

import it.polito.tdp.alien.model.AlienDictionary;
import it.polito.tdp.alien.model.Word;
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
    	boolean isAdd = false;
    	String text = txtTradurre.getText();
    	txtTradotto.clear();
    	
    	//Controllo errori input
    	if(text.length() == 0) 
    	{
    		txtTradotto.appendText("Inserire almeno un parametro\n");
    		return;
    	}
    
    	String pattern = "[A-Za-z ]*";
    	if(!text.matches(pattern)) 
    	{
    		txtTradotto.appendText("Parametro non valido, gli unici caratteri ammessi sono [A-Za-z]\n");
    		return;
    	}
    	
    	String array[] = text.split(" ");
    	if(array.length > 2) 
    	{
    		txtTradotto.appendText("Input non valido, inserire massimo due parametri\n");
    		return;
    	}
    	//Fine controllo
    	
    	String alienWord = array[0];
    	String translation = null;
    	
    	if(array.length == 1)
    		isAdd = false;
    	else 
    	{
    		isAdd = true;
    		translation = array[1];
    	}
    	
    	if(isAdd) 
    	{
    		try {
    			this.model.addWord(alienWord, translation);
    			txtTradotto.appendText("Nuovo elemento aggiunto al dizionario\n");
    		} catch(IllegalStateException ise) {
    			txtTradotto.appendText(ise.getMessage());
    		}
    	}
    	else
    	{
    		Word result = this.model.translateWord(alienWord);
    		if(result != null)
    			txtTradotto.appendText(result.getTraduzione()+"\n");
    		else
    			txtTradotto.appendText("Parola non ancora presente nel dizionario\n");	
    	}
    	
    	/*
    	String pt = txtTradurre.getText();
    	String p = "";
    	String t = "";
    	if(pt.contains(" "))
    	{
    		StringTokenizer st = new StringTokenizer(pt, " ");
    		p = st.nextToken().trim();
    		t = st.nextToken().trim();
    	}
		if(t.compareTo("")!=0)
			model.addWord(p, t);
		else
			txtTradotto.appendText(""+model.translateWord(p)+"\n");
    	 */
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
