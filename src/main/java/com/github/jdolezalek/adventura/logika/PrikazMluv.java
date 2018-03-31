/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.jdolezalek.adventura.logika;



/*******************************************************************************
 * Instance třídy PrikazMluv slouží k interakci s postavami ve hře
 *
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Doležálek
 * @version   1.00.000
 */
public class PrikazMluv implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================

    private static final String NAZEV = "mluv";
    private HerniPlan plan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor metody
     *  
     * @param plan herní plán
     */
    public PrikazMluv(HerniPlan plan)
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
        if (parametry.length < 1) {
            return "Nevím,s kým mám mluvit";
        }
        
        Postavy postava = plan.getAktualniProstor().odeberPostavu(parametry[0]);
        
        if(postava == null){
            return "Tato postava tu není";
        }
        
        plan.getAktualniProstor().vlozPostavu(postava);
        
        plan.setOdemika(postava.getOdemika());
        
        return postava.getProslov();
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
