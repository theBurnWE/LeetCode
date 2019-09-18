package originTest.util;

import java.math.BigDecimal;

public class BigDecimalTest {

    public static void main(String[] args) {
        BigDecimal test1 = new BigDecimal("1");
        BigDecimal test2 = new BigDecimal("1.0");
        BigDecimal test3 = new BigDecimal("1.1");
        BigDecimal test4 = new BigDecimal("0.9");


        System.out.println(test1.compareTo(test1));
        System.out.println(test1.compareTo(test2));
        System.out.println(test1.compareTo(test3));
        System.out.println(test1.compareTo(test4));

    }
}
