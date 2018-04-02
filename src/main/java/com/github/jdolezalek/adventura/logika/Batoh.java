/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.jdolezalek.adventura.logika;
import java.util.Map;
import java.util.HashMap;

import java.util.Observable;

/*******************************************************************************
 * Třída Batoh - popisuje inventář.
 * 
 *  Tato třída je součástí jednoduché textové hry.
 *  
 *  "Batoh" reprezentuje "úložný prostor" pro sebrané věci (příkazem seber). 
 *  Věci jsou přenositelné. Jsou vkládány do batohu a kdykoli si pomocí příkazu
 *  'batoh' můžeme zjistit jeho obsah (vypíše se řetězec znaků). Lze je z batohu
 *  odebrat, resp. je "vymazat" příkazem zahod.
 *
 * @author    Jan Doležálek
 * @version   1.00.000
 */
public class Batoh extends Observable
{
    //== Datové atributy (statické i instancí)======================================

    private final static int KAPACITA = 5; //kolik věcí lze vložit do batohu
    private Map<String, Vec> veci; //klíč a k němu přiřazená hodnota
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor třídy batoh, vytvářející novou mapu předmětů
     */
    public Batoh()
    {
        veci = new HashMap<>(); //vytvořená nová mapa, do které se vkládají předměty
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Metoda k vypsání obsahu batohu.
     * 
     * @return vrací obsah batohu, nebo zprávu o tom, že je batoh prázdný
    */
    public String toString(){
        if(veci.isEmpty()) { //počet prvků uložených v mapě je nula
            return "Batoh je prazdný.";
        }
        String result = "Obsah: ";
        for(String s : veci.keySet()) { //procházení mapy; vrací množinu obsahující klíče - předměty, které jsou v batohu
            result += s+ ", ";
        }
        return result;
    }
    
    /**
     * Metoda rozhodne, zda batoh obsahuje danou věc
     * 
     * @return zda batoh obsahuje věc - true/false
     */
    public boolean obsahujeVec(String nazev) {
        return veci.containsKey(nazev); //pokud je klíč obsažen v mapě, vrací true
    }
    
    /**
     * Metoda vloží věc do batohu.
     * 
     * @return instance vložené věci, nebo null
     */
    public Vec vlozVec(Vec vec) {
        veci.put(vec.getNazev(),vec); //vloží klíč a hodnotu do mapy
        if(veci.containsKey(vec.getNazev())) {
        	return vec;}
        return null;
    }
    
    /**
     * Metoda odebere věc z batohu.
     * 
     * @return instance odebrané věci
     */
    public Vec odeberVec(String nazev) {
        return veci.remove(nazev); //v mapě se zrusí odpovídající klíč s hodnotou
    }

    /**
     * Metoda vrací kapacitu batohu.
     * 
     * @return kapacita batohu
     */
    public int getKapacita() {
      return KAPACITA;
    }
    
    /**
     * Metoda vrací zda se do batohu vejde další věc
     * 
     * @return true pokud je v batohu místo
     */
    public boolean vejdeSeVec()
    {
        if(veci.size() < KAPACITA){
            return true;
        }
        return false;
        
    }
   
    //== Soukromé metody (instancí i třídy) ========================================

}
