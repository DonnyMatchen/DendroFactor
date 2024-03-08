package com.donny.dendrofactor;

import java.math.BigInteger;

public class Functions {
    /**
     * converts bits in a BigInteger to a boolean array, ordered from LSB to MSB
     *
     * @param x a positive integer
     * @return boolean array of bits from LSB to MSB
     * @throws IllegalArgumentException if x is negative
     */
    public static boolean[] getLsbVector(BigInteger x) {
        String z = x.toString(2);
        if (z.contains("-")) {
            throw new IllegalArgumentException("Only positive BigIntegers can be inverted");
        } else {
            boolean[] out = new boolean[z.length()];
            for (int i = 0; i < out.length; i++) {
                int j = out.length - 1 - i;
                out[i] = z.charAt(j) == '1';
            }
            return out;
        }
    }

    /**
     * raises <code>base</code> to <code>exponent</code> with square and multiply
     *
     * @param base     base
     * @param exponent exponent
     * @return <code>base ^ exponent</code>
     */
    public static BigInteger exp(BigInteger base, BigInteger exponent) {
        boolean[] map = Functions.getLsbVector(exponent);
        BigInteger out = BigInteger.ZERO;
        BigInteger temp = base;
        for (boolean bit : map) {
            if (bit) {
                out = out.add(temp);
            }
            temp = temp.pow(2);
        }
        return out;
    }
}