package com.github.jdolezalek.adventura.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.jdolezalek.adventura.logika.Hra;
import com.github.jdolezalek.adventura.logika.IHra;
import com.github.jdolezalek.adventura.logika.Prostor;


import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class homeController extends GridPane implements Observer {
	
	@FXML private TextField textVstup;
	@FXML private TextArea textVypis;
	@FXML private Button odesli;
	@FXML private ListView<Prostor> seznamMistnosti;
	@FXML private MenuItem menuItemEnd;
	@FXML private MenuItem menuItemNewGame;
	@FXML private MenuItem menuItemAbout;
	@FXML private MenuItem menuItemHelp;
	@FXML private ImageView imageViewMap;
	@FXML private ImageView imageViewBatoh1;
	@FXML private ImageView imageViewBatoh2;
	@FXML private ImageView imageViewBatoh3;
	@FXML private ImageView imageViewBatoh4;
	@FXML private ImageView imageViewBatoh5;
	@FXML private ImageView imageViewBatoh6;
	@FXML private ImageView imageViewBatoh7;
	@FXML private ImageView imageViewBatoh8;
	
	JFrame oknoMapa;
    JPanel mapaPanel;
    ImageIcon mapaIcon;
    JLabel obrazekLabel;
	
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
		
		oknoMapa = new JFrame();
        oknoMapa.setDefaultCloseOperation(3);
        oknoMapa.setTitle("Mapa");
        oknoMapa.setSize(1024, 640);
        mapaPanel = new JPanel();
        
        oknoMapa.add(mapaPanel);
        
        URL umisteniObrazku = this.getClass().getResource("/herniPlan.png");
        mapaIcon = new ImageIcon(umisteniObrazku);
        obrazekLabel = new JLabel(mapaIcon);
        
        mapaPanel.add(obrazekLabel);
        
        oknoMapa.setVisible(true);
		
	}
	
	public void novaHra() {
		
		imageViewMap.setImage(new Image("/herniPlan.png"));
		imageViewBatoh1.setImage(null);
		imageViewBatoh2.setImage(null);
		imageViewBatoh3.setImage(null);
		imageViewBatoh4.setImage(null);
		imageViewBatoh5.setImage(null);
		imageViewBatoh6.setImage(null);
		imageViewBatoh7.setImage(null);
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

	public void jdi () {
		textVstup.setText("jdi ");
	}
	
	public void vezmi () {
		textVstup.setText("vezmi ");
	}
	
	public void zahod () {
		textVstup.setText("zahod ");
	}
	
	public void prozkoumej () {
		textVstup.setText("prozkoumej ");
	}
	
	public void mluv () {
		textVstup.setText("mluv ");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		seznamMistnosti.getItems().clear();
		seznamMistnosti.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());
		
		
		if(hra.getHerniPlan().getAktualniProstor().getNazev().equals("hlavní_město")) {
		    imageViewMap.setImage(new Image("/herniPlan_hlavniMesto.png"));

		    URL umisteniObrazku = this.getClass().getResource("/herniPlan_hlavniMesto.png");
	        mapaIcon = new ImageIcon(umisteniObrazku);
	        obrazekLabel = new JLabel(mapaIcon);
	        mapaPanel.removeAll();
	        mapaPanel.add(obrazekLabel);
	        oknoMapa.setVisible(true);
		}
		if(hra.getHerniPlan().getAktualniProstor().getNazev().equals("hospoda")) {
			imageViewMap.setImage(new Image("/herniPlan.png"));
			URL umisteniObrazku = this.getClass().getResource("/herniPlan.png");
	        mapaIcon = new ImageIcon(umisteniObrazku);
	        obrazekLabel = new JLabel(mapaIcon);
	        mapaPanel.removeAll();
	        mapaPanel.add(obrazekLabel);
	        oknoMapa.setVisible(true);
		}
		    
		if(hra.getHerniPlan().getAktualniProstor().getNazev().equals("les")) {
			imageViewMap.setImage(new Image("/herniPlan_les.png"));
			URL umisteniObrazku = this.getClass().getResource("/herniPlan_les.png");
        	mapaIcon = new ImageIcon(umisteniObrazku);
        	obrazekLabel = new JLabel(mapaIcon);
        	mapaPanel.removeAll();
        	mapaPanel.add(obrazekLabel);
        	oknoMapa.setVisible(true);
		    }
		if(hra.getHerniPlan().getAktualniProstor().getNazev().equals("hluboký_les")) {
		    imageViewMap.setImage(new Image("/herniPlan_hlubokyLes.png"));
		    URL umisteniObrazku = this.getClass().getResource("/herniPlan_hlubokyLes.png");
	        mapaIcon = new ImageIcon(umisteniObrazku);
	        obrazekLabel = new JLabel(mapaIcon);
	        mapaPanel.removeAll();
	        mapaPanel.add(obrazekLabel);
	        oknoMapa.setVisible(true);
	        }
		if(hra.getHerniPlan().getAktualniProstor().getNazev().equals("k_elfům")) {
		    imageViewMap.setImage(new Image("/herniPlan2.png"));
		    URL umisteniObrazku = this.getClass().getResource("/herniPlan2.png");
		    mapaIcon = new ImageIcon(umisteniObrazku);
		    obrazekLabel = new JLabel(mapaIcon);
		    mapaPanel.removeAll();
		    mapaPanel.add(obrazekLabel);
		    oknoMapa.setVisible(true);
        }
		if(hra.getHerniPlan().getAktualniProstor().getNazev().equals("nádvoří")) {
		    imageViewMap.setImage(new Image("/herniPlan2_nadvori.png"));
	   		URL umisteniObrazku = this.getClass().getResource("/herniPlan2_nadvori.png");
	   		mapaIcon = new ImageIcon(umisteniObrazku);
	   		obrazekLabel = new JLabel(mapaIcon);
	   		mapaPanel.removeAll();
	   		mapaPanel.add(obrazekLabel);
	   		oknoMapa.setVisible(true);
			}
		if(hra.getHerniPlan().getAktualniProstor().getNazev().equals("věž_mágů")) {
		    imageViewMap.setImage(new Image("/herniPlan2_vezMagu.png"));
			URL umisteniObrazku = this.getClass().getResource("/herniPlan2_vezMagu.png");
			mapaIcon = new ImageIcon(umisteniObrazku);
			obrazekLabel = new JLabel(mapaIcon);
			mapaPanel.removeAll();
			mapaPanel.add(obrazekLabel);
			oknoMapa.setVisible(true);
		}
		
		if(hra.getHerniPlan().getBatoh().obsahujeVec("meč"))
			imageViewBatoh1.setImage(new Image("/mec.png"));
		if(hra.getHerniPlan().getBatoh().obsahujeVec("zlaťáky"))
			imageViewBatoh2.setImage(new Image("/zlataky.png"));
		if(hra.getHerniPlan().getBatoh().obsahujeVec("luk"))
			imageViewBatoh3.setImage(new Image("/luk.png"));
		if(hra.getHerniPlan().getBatoh().obsahujeVec("sipy"))
			imageViewBatoh4.setImage(new Image("/sipy.png"));
		if(hra.getHerniPlan().getBatoh().obsahujeVec("klobasa"))
			imageViewBatoh5.setImage(new Image("/klobasa.png"));
		if(hra.getHerniPlan().getBatoh().obsahujeVec("pivo"))
			imageViewBatoh6.setImage(new Image("/pivo.png"));
		if(hra.getHerniPlan().getBatoh().obsahujeVec("lahev"))
			imageViewBatoh7.setImage(new Image("/lahev.png"));
		
		if(!hra.getHerniPlan().getBatoh().obsahujeVec("meč"))
			imageViewBatoh1.setImage(null);
		if(!hra.getHerniPlan().getBatoh().obsahujeVec("zlaťáky"))
			imageViewBatoh2.setImage(null);
		if(!hra.getHerniPlan().getBatoh().obsahujeVec("luk"))
			imageViewBatoh3.setImage(null);
		if(!hra.getHerniPlan().getBatoh().obsahujeVec("sipy"))
			imageViewBatoh4.setImage(null);
		if(!hra.getHerniPlan().getBatoh().obsahujeVec("klobasa"))
			imageViewBatoh5.setImage(null);
		if(!hra.getHerniPlan().getBatoh().obsahujeVec("pivo"))
			imageViewBatoh6.setImage(null);
		if(!hra.getHerniPlan().getBatoh().obsahujeVec("lahev"))
			imageViewBatoh7.setImage(null);
		
		
		
	}
}
