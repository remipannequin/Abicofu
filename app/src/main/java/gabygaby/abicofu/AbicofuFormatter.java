package gabygaby.abicofu;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a number as an Abicofu text
 *
 * Created by Rémi Pannequin on 04/07/15.
 */
public class AbicofuFormatter {


    private final long number;
    private static final String ZERO = "zero";
    private static final String[] NAMES = {"", "b", "c", "d", "f", "g", "h", "k"};

    private static final String LETTER_ODD = "a";
    private static final String[] LETTERS = new String[]{"", "i", "o", "u"};
    private static final String LETTER_OTHER = "e";

    private static final String MULT_MINOR = "tet";
    private static final String SEP_MINOR = "-";
    private static final String MULT_MAJOR = "ct";
    private static final String SEP_MAJOR = " ";


    public AbicofuFormatter(long n) {
        this.number = n;
    }


    public static String write_octet(int n) {
        if (n == 0) return ZERO;
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


    public static String write_digit(int d) {
        if (d == 0) return ZERO;
        int even = d - d % 2;
        StringBuilder result = new StringBuilder();
        result.append(NAMES[even / 2]);
        int p = 0;
        while (Math.pow(2,p) < even) {
            p++;
        }
        if (even == 0 || Math.pow(2,p) == even){
            result.append(LETTERS[p]);
        } else {
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
        List<String> oct_names = new ArrayList<>();
        while (tmp != 0) {
            //extract leftmost octet
            int o = (int)(tmp % 256);
            oct_names.add(0, write_octet(o));
            tmp -= o;
            tmp /= 256;
        }
        StringBuilder result = new StringBuilder();

        int n = oct_names.size();
        for (String oct : oct_names) {
            result.append(oct);
            if (n != 1) {
                result.append(SEP_MAJOR).append(write_octet(n-1)).append(MULT_MAJOR).append(", ");
            }
            n--;
        }
        return result.toString();

    }
















}
