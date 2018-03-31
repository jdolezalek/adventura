package com.github.jdolezalek.adventura.logika;

import java.util.Observable;
/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * 
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory, propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova, Jan Riha
 * @version    ZS 2016/2017
 */
public class HerniPlan extends Observable {

    private static final String CILOVY_PROSTOR = "doly";

    private Prostor aktualniProstor;
    private Batoh batoh;

    private boolean les;
    private boolean elfiMesto;
    private boolean doly;
    private boolean maPovolenku;
    private boolean maPlan;
    private boolean svitek;
    private boolean planDoly;

    /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví hospodu.
     */
    public HerniPlan() {
        zalozProstoryHry();

        this.batoh = new Batoh();
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví hospodu.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor hospoda = new Prostor("hospoda","hospoda v hlavním městě, kde jsi se probudil");
        Prostor hlavniMesto = new Prostor("hlavní_město","nádvoří hlavního města");
        Prostor les = new Prostor("les","les, v dálce vidíš 2 elfy střežící cestu");
        Prostor hlubokyLes = new Prostor("hluboký_les","temný les.. Cítíš že všude se najednou tak ochladilo..\nPovídá se, že zde lze potkat nemrtvé\nV dálce vidíš slizkou mrtvolu. Pomalu se k tobě přibližuje a pokud nemáš zbraň, je stebou konec.");
        Prostor elfiMesto = new Prostor("k_elfům","u brány hlavního elfího města");
        Prostor nadvori = new Prostor ("nádvoří","elfího města. Lze se odsud dostat k čarodějům");
        Prostor vezMagu = new Prostor ("věž_mágů","kde se nachází nejvyšší mág");
        Prostor doly = new Prostor(CILOVY_PROSTOR, "kde se nachází nemrtví čaroděj!");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        hospoda.setVychod(hlavniMesto);
        hlavniMesto.setVychod(hospoda);
        hlavniMesto.setVychod(les);
        les.setVychod(hlavniMesto);
        les.setVychod(hlubokyLes);
        les.setVychod(elfiMesto);
        hlubokyLes.setVychod(les);
        hlubokyLes.setVychod(elfiMesto);
        elfiMesto.setVychod(nadvori);
        nadvori.setVychod(vezMagu);
        vezMagu.setVychod(nadvori);
        nadvori.setVychod(elfiMesto);
        elfiMesto.setVychod(doly);

        //nepřístupné prostory zde zamkneme
        les.zamknout(true);
        elfiMesto.zamknout(true);
        doly.zamknout(true);


        // vytvoříme několik věcí
        Vec rezavyMec = new Vec("meč", "Rezavý meč který vypadá, že se každou chvílí rozpadne", true);
        Vec skrin = new Vec("dřevěná_skříň", "Vidíš v ní jenom starý spoďáry", false);
        Vec postel = new Vec ("postel","Nic extra, ale spát se na ní dalo",false);
        Vec studna = new Vec ("studna","Možná bych si mohl vzít trochu vody sebou...",false);
        Vec zlataky = new Vec("zlaťáky","A hele peníze, ty se vzdycky mohou hodit",true);
        Vec luk = new Vec("luk","Ještě se naučit z něj střílet a mít šípy.",true);
        Vec cedule = new Vec("cedule","Královská cesta k elfímu městu",false);
        Vec cedule2 = new Vec("zrezlá_cedule","zapovězená cesta k elfímu městu",false);
        Vec sipy = new Vec("sipy","k luku se mohou hodit", true);
        Vec knihovna = new Vec("knihovna","tohle nepřečtu ani za celý svůj život",false);
        Vec klobasa = new Vec("klobasa","něco k snědku",true);
        Vec pivo = new Vec("pivo","to se hodí vždycky",true);
        Vec lahev = new Vec("lahev","mohla by se hodit na vodu",true);
        

        // vytvoříme několik postav
        Postavy hospodsky = new Postavy("hospodský","No konečně jsi se probral, pojď odneseme tady ty sudy do sklepa a pak můžeš jít.\nJo a za tvou námahu tu mám pro tebe mapu.\nTa ti usnadní cestu přes les, jestli se opravdu chceš vydat pryč.","les");
        Postavy opilec = new Postavy("opilec","Na co čumíš, chceš jednu natáhnout?!","");
        Postavy straz = new Postavy("stráž","Jestli se něco chceš dozvědět, spíš jdi za naším kapitánem","");
        Postavy vedouciStraze = new Postavy("hlavní_stráž","Zdravím tě dobrodruhu! Je dobře že jsi za mnou přišel,\nPokud chceš opustit toto město, tak potřebuješ propustku, jelikož věřím, že nejsi špeh, tak ti jí dám.","maPovolenku");
        Postavy elf = new Postavy("elf","Dávej si pozor kam našlapuješ, děláš hrozný hluk..\nPokud jsi na cestě do elfího města, jdi raději po hlavní cestě a nezahýbej támhle u toho starého stromu.\nPovídá se, že v hlubokém lese jsou nemrtví..","elfiMesto");
        Postavy elf2 = new Postavy("elf_stráž","Vy lidé naděláte tolik hluku..","");
        Postavy ghoul = new Postavy("ghoul", "ghhhh... Zabil jsi nemrtvého a nyní se můžeš vydat dál.","exp");
        Postavy carodej = new Postavy("čaroděj","Konečně jsi tu! Vem si tento svitek který stačí na čaroděje seslat.\nJo a projdi si knihovnu může se ti to hodit.\nJeště teda zajdi za knihovníkem , poradí ti jak se dostat k temným dolům","svitek");
        Postavy knihovnik = new Postavy("knihovník","Huh? Temné doly? Ach ano, už si vzpomínám, někde jsem tady měl mapu...\nTady je! Na vezmi si jí a šťastnou cestu!","dul");
        
        
        
        // umístíme věci do prostorů
        hospoda.vlozVec(rezavyMec);
        hospoda.vlozVec(skrin);
        hospoda.vlozVec(postel);
        hospoda.vlozVec(klobasa);
        hospoda.vlozVec(pivo);
        hlavniMesto.vlozVec(studna);
        hlavniMesto.vlozVec(zlataky);
        hlavniMesto.vlozVec(lahev);
        les.vlozVec(luk);
        les.vlozVec(cedule);
        hlubokyLes.vlozVec(cedule2);
        hlubokyLes.vlozVec(sipy);
        vezMagu.vlozVec(knihovna);
        
        // umístíme postavy do prostorů
        hospoda.vlozPostavu(hospodsky);
        hospoda.vlozPostavu(opilec);
        hlavniMesto.vlozPostavu(vedouciStraze);
        hlavniMesto.vlozPostavu(straz);
        les.vlozPostavu(elf);
        les.vlozPostavu(elf2);
        hlubokyLes.vlozPostavu(ghoul);
        elfiMesto.vlozPostavu(elf2);
        nadvori.vlozPostavu(elf2);
        vezMagu.vlozPostavu(carodej);
        vezMagu.vlozPostavu(knihovnik);
        

        aktualniProstor = hospoda;  // hra začíná v hospodě
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
       this.setChanged();
       this.notifyObservers();
    }

    /**
     *  Metoda nastaví zda hráč vyhrál, na základě místnosti
     *
     *@return vrací zda hráč vyhrál, nebo ne
     */    

    public boolean hracVyhral() {
        if (aktualniProstor.getNazev().equals(CILOVY_PROSTOR)) {
            return true;
        }
        
        return false;
    }

    /**
     *  Metoda nastaví zda hráč prohrál, na základě místnosti a obsahu batohu
     *
     *@return vrací zda hráč prohrál, nebo ne
     */    

    public boolean hracProhral(){
        if (!batoh.obsahujeVec("meč") && aktualniProstor.getNazev().equals("hluboký_les") ) {
            return true;
        }        
        return false;
    }
    
    /**
     * Metoda vrací obsah batohu.
     * 
     * @return instance třídy batoh
     */
    public Batoh getBatoh() {
        return batoh;
    }

    
    /**
     * Metoda zjišťuje splnění podmínek a nastavuje proměnnou povolení ke vstupu do zamčených místností.
     * V příkazu jdi se poté místnosti odemknou.
     * 
     * @param String odemknout (proměnná postavy)
     */
    public void setOdemika(String odemknout){
        
        switch (odemknout){
            case "les":
                maPlan = true;
                if(maPovolenku) {
                    les = true;
                }
                break;
            case "elfiMesto":
                elfiMesto = true;
                break;  
            case "dul":
                planDoly = true;
                if(svitek){
                    doly = true;
                }
                break;
            case "maPovolenku":
                maPovolenku = true;
                if (maPlan){ 
                    les = true;
                }
                break;
            case "exp":
                elfiMesto = true;
                break;
            case "svitek":
                svitek = true;
                if(planDoly){
                    doly = true;
                }
                break;
            default:
                break;
        }
    }

    //3 gettery pro booly které nastavují přístup do uzamčených lokací

        /**
     * Zjišťuje zda má postava přístup do dané lokace
     * 
     * @return má/nemá přístup
     */
    public boolean getLes()
    {
        return les;
    }

     /**
     * Zjišťuje zda má postava přístup do dané lokace
     * 
     * @return má/nemá přístup
     */
    public boolean getElfiMesto()
    {
        return elfiMesto;
    }

    /**
     * Zjišťuje zda má postava přístup do dané lokace
     * 
     * @return má/nemá přístup
     */
    public boolean getDoly()
    {
        return doly;
    }
}
