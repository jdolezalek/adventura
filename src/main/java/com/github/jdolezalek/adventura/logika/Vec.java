/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.jdolezalek.adventura.logika;



/*******************************************************************************
 * Instance třídy věci nastavují vlastnosti věcí ve hře (jméno, popis a zda se dají přenášet).
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * Věci lze vložit do batohu, nebo prozkoumat.
 *
 * @author    Jan Doležálek
 * @version   1.00.000
 */
public class Vec
{
    //== Datové atributy (statické i instancí)======================================
    private String nazev;
    private String popis;
    private boolean prenositelna;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor metody
     */
    public Vec(String nazev, String popis, boolean prenositelna)
    {
        this.nazev = nazev;
        this.popis = popis;
        this.prenositelna = prenositelna;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    public String getNazev() {
        return nazev;
    }
    
    public String getPopis() {
        return popis;
    }
    
    public boolean isPrenositelna() {
        return prenositelna;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
