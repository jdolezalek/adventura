/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.jdolezalek.adventura.logika;



/*******************************************************************************
 * Instance třídy PrikazVezmi umožňuje hráči vzít věci v herním prostoru
 * a uložit je, pokud je to možné, do batohu
 * 
 * Tato třída je součástí jednoduché textové hry.
 *
 * @author    Jan Doležálek
 * @version   1.00.000
 */
public class PrikazVezmi implements IPrikaz
{
    //== Datové atributy (statické i instancí)======================================
    private static final String NAZEV = "vezmi";
    
    private HerniPlan herniPlan;

    //== Konstruktory a tovární metody =============================================

    /***************************************************************************
     *  Konstruktor metody
     */
    public PrikazVezmi(HerniPlan hPlan)
    {
        this.herniPlan = hPlan;
    }

    //== Nesoukromé metody (instancí i třídy) ======================================
                /**
     *  Metoda pro provedení příkazu ve hře.
     *  Počet parametrů je závislý na konkrétním příkazu,
     *  např. příkazy konec a napoveda nemají parametry
     *  příkazy jdi, seber, polož mají jeden parametr
     *  stejně jako tyto příkazy, má i tento 1 parametr
     *  
     *  @param parametry: jeden parametr a tím je věc
     *  
     *  @return zpráva, kterou vypíše hra hráči
     */
    public String proved(String... parametry) {
        if (parametry.length < 1) {
            return "nevím, co mám sebrat";
        }
        
        String nazevVeci = parametry[0];
        
        Vec vec = herniPlan.getAktualniProstor().odeberVec(nazevVeci);
        
        //ověření zda se věc nachází v prostoru
        if (vec == null) {
            return "věc '" + nazevVeci + "' tu není";
        }
        
        //ověření zda je věc přenositelná
        
        if (!vec.isPrenositelna()) {
            herniPlan.getAktualniProstor().vlozVec(vec);
            return "věc '" + nazevVeci + "' fakt neuneseš";
        }
        
        // Ověřování kapacity batohu a vkládání věci do batohu
        
         if (!herniPlan.getBatoh().vejdeSeVec()) {
               herniPlan.getAktualniProstor().vlozVec(vec);
               return "batoh je plný";
          }

          //uložení do batohu
           herniPlan.getBatoh().vlozVec(vec);
           return "věc '" + nazevVeci + "' jsi uložil do batohu";
    }

            /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *  
     *  @return nazev prikazu
     */
	public String getNazev() {
	    return NAZEV;
	}

    //== Soukromé metody (instancí i třídy) ========================================

}
