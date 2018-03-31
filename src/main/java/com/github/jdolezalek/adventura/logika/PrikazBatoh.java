/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.jdolezalek.adventura.logika;



/*******************************************************************************
 * Instance třídy PrikazBatoh vypíše obsah hráčova inventáře
 * pomocí bezparametrového příkazu batoh
 * 
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Doležálek
 * @version   1.00.000
 */
public class PrikazBatoh implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "batoh";
    private HerniPlan plan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor metody
     */
    public PrikazBatoh(HerniPlan plan)
    {
        this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================

            /**
     *  Metoda pro provedení příkazu ve hře.
     *  Počet parametrů je závislý na konkrétním příkazu,
     *  např. příkazy konec a napoveda nemají parametry
     *  příkazy jdi, seber, polož mají jeden parametr
     *  příkaz pouzij může mít dva parametry.
     *  
     *  @param parametry počet parametrů závisí na konkrétním příkazu.
     *  
     */
    public String proved(String... parametry){
        if (parametry.length > 0) {
            return "Stačí zadat jenom batoh a vypíše se obsah inventáře";
        }
        
        return plan.getBatoh().toString();
    }
     /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
    public String getNazev(){
        
        return NAZEV;
    }

    //== Soukromé metody (instancí i třídy) ========================================

}
