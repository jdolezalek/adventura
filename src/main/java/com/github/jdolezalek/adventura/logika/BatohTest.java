/* Soubor je ulozen v kodovani UTF-8.
 * Kontrola kódování: Příliš žluťoučký kůň úpěl ďábelské ódy. */
package com.github.jdolezalek.adventura.logika;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování třídy ... 
 *
 * @author    Jan Doležálek
 * @version   1.00.000
 */
public class BatohTest
{
    //== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    /***************************************************************************
     * Inicializace předcházející spuštění každého testu a připravující tzv.
     * přípravek (fixture), což je sada objektů, s nimiž budou testy pracovat.
     */
    @Before
    public void setUp()
    {
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každého testu.
     */
    @After
    public void tearDown()
    {
    }

    //== VLASTNÍ TESTY =========================================================
     /**
     * testuje, zda jde věc vložit do batohu a jestli ji pak obsahuje
     */
    @Test
    public void testVlozDoBatohu()
    {
        Batoh batoh1 = new Batoh();
        Vec vec1 = new Vec("a","b",true);
        batoh1.vlozVec(vec1);
        assertEquals(true, batoh1.obsahujeVec("a"));
        assertEquals(false, batoh1.obsahujeVec("b"));
    }
         /**
     * testuje, zda nejde vložit věc nad kapacitu batohu
     * také testuje zda nejde vložit do batohu nepřenositelné věci
     * 
     */
    @Test
    public void testKapacita()
    {
       Batoh batoh = new Batoh();
        
       Vec test1 = new Vec ("test1","test",true);
       Vec test2 = new Vec ("test2","test",true);
       Vec test3 = new Vec ("test3","test",true);
       Vec test4 = new Vec ("test4","test",false);
       Vec test5 = new Vec ("test4","test",true);
       Vec test6 = new Vec ("test5","test",true);
       batoh.vlozVec (test1);
       assertEquals(true, batoh.vejdeSeVec());
       batoh.vlozVec (test2);
       assertEquals(true, batoh.vejdeSeVec());
       batoh.vlozVec (test3);
       assertEquals(true, batoh.vejdeSeVec());
       batoh.vlozVec (test4);
       assertEquals(true, batoh.vejdeSeVec());
       batoh.vlozVec (test5);
       assertEquals(true, batoh.vejdeSeVec());
       batoh.vlozVec (test6);
       assertEquals(false, batoh.vejdeSeVec());
    
    }
}
