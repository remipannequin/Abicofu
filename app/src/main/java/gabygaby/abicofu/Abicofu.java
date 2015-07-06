package gabygaby.abicofu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by remi on 04/07/15.
 */
public class Abicofu {


    private final long number;
    String ZERO = "zero";
    String[] NAMES = {"", "b", "c", "d", "f", "g", "h", "k"};

    String LETTER_ODD = "a";
    String[] LETTERS = new String[]{"", "i", "o", "u"};
    String LETTER_OTHER = "e";

    String MULT_MINOR = "tet";
    String SEP_MINOR = "-";
    String MULT_MAJOR = "ct";
    String SEP_MAJOR = " ";


    public Abicofu(long n) {
        this.number = n;
    }


    public String write_octet(int n) {

        //extracts digits
        int minor = n % 16;
        int major = (n - minor) / 16;
        if (major != 0) {
            if (minor != 0) {
                return write_digit(major) + MULT_MINOR + SEP_MINOR + write_digit(minor);
            } else {
                return write_digit(major) + MULT_MINOR;
            }
        } else {
            return write_digit(minor);
        }
    }


    private String write_digit(int d) {
        int even = d - d % 2;
        StringBuilder result = new StringBuilder();
        result.append(NAMES[even / 2]);
        int p = 0;
        while (Math.pow(2,p) <= d) {
            p++;
        }
        if (Math.pow(2,p) < d){
            result.append(LETTERS[p]);
        } else{
            result.append(LETTER_OTHER);
        }
        if (d % 2 == 1) {
            result.append(LETTER_ODD);
        }
        return result.toString();
    }


    public String write() {
        //convert number to base 16
        long tmp = this.number;
        if (tmp == 0) {
            return ZERO;
        }
        List<String> oct_names = new ArrayList();
        while (tmp != 0) {
            //extract leftmost octet
            int o = (int)(tmp % 256);
            oct_names.add(write_octet(o));
            tmp -= o;
            tmp /= 256;
        }
        StringBuilder result = new StringBuilder();
        result.append(oct_names.get(0));
        int n = oct_names.size();
        for (String oct : oct_names) {
            result.append(oct).append(SEP_MAJOR).append(write_octet(n)).append(MULT_MAJOR);
            n--;
        }
        return result.toString();

    }
















}
