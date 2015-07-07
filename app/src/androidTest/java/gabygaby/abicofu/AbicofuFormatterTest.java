package gabygaby.abicofu;


import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.TestCase;



/**
 * Tests for AbicofuFormatter
 *
 * Created by Rémi Pannequin on 06/07/15.
 */
public class AbicofuFormatterTest extends TestCase {

    @SmallTest
    public void testWriteOctet() throws Exception {
        String[] expected1 = new String[]{"a", "atet-a", "bitet-a", "biatet-a", "cotet-a", "coatet-a", "detet-a", "deatet-a", "futet-a", "fuatet-a", "getet-a", "geatet-a", "hetet-a", "heatet-a", "ketet-a", "keatet-a"};
        String[] expected2 = new String[]{"cotet", "cotet-a", "cotet-bi", "cotet-bia", "cotet-co", "cotet-coa", "cotet-de", "cotet-dea", "cotet-fu", "cotet-fua", "cotet-ge", "cotet-gea", "cotet-he", "cotet-hea", "cotet-ke", "cotet-kea"};
        for (int i = 0; i < 16; i++){
            assertEquals("testing write_octet: Xtet-a", expected1[i], AbicofuFormatter.write_octet(i*16+1));
        }

        for (int i = 0; i < 16; i++){
            assertEquals("testing write_octet: cotet-X", expected2[i], AbicofuFormatter.write_octet(4*16+i));
        }
    }

    @SmallTest
    public void testWriteZero() throws Exception {
        AbicofuFormatter instance = new AbicofuFormatter(0);
        assertEquals("testing zero", "zero", instance.write());
        assertEquals("testing zero", "zero", AbicofuFormatter.write_octet(0));
        assertEquals("testing zero", "zero", AbicofuFormatter.write_digit(0));
    }

    @SmallTest
    public void testWriteDigit()  throws Exception {
        String[] expected = new String[]{"a", "bi", "bia", "co", "coa", "de", "dea", "fu", "fua", "ge", "gea", "he", "hea", "ke", "kea"};
        for (int i = 1; i < 16; i++){

            assertEquals("testing write_digit", expected[i - 1], AbicofuFormatter.write_digit(i));
        }
    }

    //@SmallTest
    public void testWrite1()  throws Exception {
        String[] expected = new String[]{"a", "bi", "bia", "co", "coa", "de", "dea", "fu", "fua", "ge", "gea", "he", "hea", "ke", "kea"};
        for (int i = 0; i < 15; i++){
            AbicofuFormatter instance = new AbicofuFormatter(i+1);
            assertEquals("testing write 1-16", expected[i], instance.write());
        }
    }


    public void testWrite2()  throws Exception {
        String expected = "biatet-co bict, keatet-kea act, detet-fu";
        int test = 0x34FF68;
        AbicofuFormatter instance = new AbicofuFormatter(test);
        assertEquals("testing write ", expected, instance.write());
    }

    public void testWrite3()  throws Exception {
        String expected = "cotet-kea coact, ketet-kea coct, biatet-hea biact, heatet-co bict, getet-ke act, futet-fu";
        long test = 0x4FEF3DD4AE88L;
        AbicofuFormatter instance = new AbicofuFormatter(test);
        assertEquals("testing write ", expected, instance.write());
    }


}