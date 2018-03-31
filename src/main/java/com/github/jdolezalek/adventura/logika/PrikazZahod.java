/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.jdolezalek.adventura.logika;



/*******************************************************************************
 * Třída PrikazZahod umožňuje hráči zbavit se věci
 * kterou má uloženou v batohu.
 *
 * @author    Jan Doležálek
 * @version   1.00.000
 */
public class PrikazZahod implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================

    private static final String NAZEV = "zahod";
    private HerniPlan plan;
    
    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor metody
     */
    public PrikazZahod(HerniPlan plan)
    {
        this.plan = plan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
    /**
     * Provádí příkaz zahoď. Zkouší zahodit věci, které jsou už vložené v batohu.
     * Parametr je jeden a tím je název věci
     * 
     * @return zpráva s výsledkem (zda se podařilo věc zahodit, či nikoliv)
     */
    @Override
    public String proved(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo (sousední prostor), tak ....
            return "Co mám zahodit?";
        }

        
        //pokud je věc v batohu, je zahozena do prostoru, kde jí lze znovu sebrat
        if(parametry.length == 1) {
            if (plan.getBatoh().obsahujeVec(parametry[0])) {
            Vec vec = plan.getBatoh().odeberVec(parametry[0]);
            plan.getAktualniProstor().vlozVec(vec);
            return "Věc byla zahozena";
            }
            else { return "Tato věc není v batohu";}
        }
        
        return "Příkaz se nepodařilo zpracovat";
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
