package com.github.jdolezalek.adventura.ui;

import java.util.Observable;
import java.util.Observer;

import com.github.jdolezalek.adventura.logika.IHra;
import com.github.jdolezalek.adventura.logika.Prostor;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class homeController extends GridPane implements Observer {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Prostor> seznamMistnosti;
	private IHra hra;
	
	public void odesliPrikaz() {
		String vstup = textVstup.getText();
		String vypis = hra.zpracujPrikaz(vstup);
		textVypis.appendText("\n \nPříkaz: "+vstup+"\n \n"+vypis);
		textVstup.setText("");
		
		if(hra.konecHry()) {
			 textVypis.appendText("\n"+"Konec hry...");
			 textVstup.setDisable(true);
			 odesli.setDisable(true);
			}

	}
	
	public void inicializuj(IHra hra) 
	{
		this.hra = hra;
		textVypis.setText(hra.vratUvitani());
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		hra.getHerniPlan().addObserver(this);
		
	}

	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
	}
}
