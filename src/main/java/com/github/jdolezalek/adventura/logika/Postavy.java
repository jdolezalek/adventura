/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.jdolezalek.adventura.logika;



/*******************************************************************************
 * Instance třídy Postavy nastavují vlastnosti postav ve hře (jméno, co říkají
 * a jakou místnost nám umožňují navštívit).
 * 
 * Tato třída je součástí jednoduché textové hry.
 * 
 * S postavami lze mluvit a pokud s některými postavami promluvíme,
 * dozvíme se, jak se dostat do dalších místností, popřípadě se aktivují jiné herní mechanismy.
 *
 * @author    Jan Doležálek
 * @version   1.00.000
 */
public class Postavy
{
    //== Datové atributy (statické i instancí)======================================

    private String jmeno;
    private String proslov;
    private String odemika;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor metody Postavy
     */
    public Postavy(String jmeno, String proslov, String odemika)
    {
        this.jmeno = jmeno;
        this.proslov = proslov;
        this.odemika = odemika;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

    /**
     * Metoda vrací jméno postavy
     * 
     * @return jméno postavy
     */
    public String getJmeno()
    {
        return jmeno;
    }
    
        /**
     * Metoda vrací co postavy říkají
     * 
     * @return proslov postavy
     */
    public String getProslov()
    {
        return proslov;
    }
    
        /**
     * Metoda vrací informaci o tom kterou místnost daná postava odemiká,
     * či jaký herní mechanismus spouští
     * 
     * @return název odemikané místnosti
     */
    
    public String getOdemika()
    {
        return odemika;
    }
    
    //== Soukromé metody (instancí i třídy) ========================================

}
