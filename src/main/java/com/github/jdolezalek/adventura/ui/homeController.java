package com.github.jdolezalek.adventura.ui;

import java.util.Observable;
import java.util.Observer;

import com.github.jdolezalek.adventura.logika.Hra;
import com.github.jdolezalek.adventura.logika.IHra;
import com.github.jdolezalek.adventura.logika.Prostor;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.MenuItem;

public class homeController extends GridPane implements Observer {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Prostor> seznamMistnosti;
	@FXML private MenuItem menuItemEnd;
	@FXML private MenuItem menuItemNewGame;
	@FXML private MenuItem menuItemAbout;
	@FXML private MenuItem menuItemHelp;
	
	private IHra hra;
	
	public static String readFile(String path, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(path));
			  return new String(encoded, encoding);
			}
	
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
	
	public void novaHra() {
		
		IHra hra = new Hra();
		this.hra = hra;
		seznamMistnosti.getItems().clear();
		textVstup.setDisable(false);
		odesli.setDisable(false);
		inicializuj(hra);
		
	}
	
	public void konecHry() {
		
		textVypis.appendText("\n"+"Konec hry...");
		textVstup.setDisable(true);
		odesli.setDisable(true);
	}
	
	public void oHre() throws IOException {
		textVypis.appendText("\n"+"\n"+readFile("README.txt", StandardCharsets.UTF_8));
	}
	
	public void napoveda() throws IOException, URISyntaxException
	{
		java.awt.Desktop.getDesktop().browse(new java.net.URI("Uzivatelska_prirucka.html"));
	}

	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
	}
}
