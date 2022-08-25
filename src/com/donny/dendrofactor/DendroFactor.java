package com.donny.dendrofactor;

import java.math.BigInteger;
import java.util.ArrayList;

public class DendroFactor {
    public static BigInteger bigExponentiation(BigInteger base, BigInteger exp) {
        ArrayList<Holder> factors = factor(exp);
        BigInteger val = base;
        for (Holder h : factors) {
            if (h.NUMBER.compareTo(BigInteger.valueOf(1000000)) < 0) {
                for (int i = 0; i < h.getPower(); i++) {
                    val = val.pow(h.NUMBER.intValue());
                }
            } else {
                val = bigExponentiation(val,
                        h.getValue().subtract(BigInteger.ONE)
                ).multiply(val);
            }
        }
        return val;
    }
    public static BigInteger bigModularExponentiation(BigInteger base, BigInteger exp, BigInteger mod) {
        ArrayList<Holder> factors = factor(exp);
        BigInteger val = base;
        for (Holder h : factors) {
            if (h.NUMBER.compareTo(BigInteger.valueOf(1000000)) < 0) {
                for (int i = 0; i < h.getPower(); i++) {
                    val = val.pow(h.NUMBER.intValue()).mod(mod);
                }
            } else {
                val = bigModularExponentiation(val,
                        h.getValue().subtract(BigInteger.ONE),
                        mod
                ).multiply(val).mod(mod);
            }
        }
        return val;
    }

    public static class Holder {
        public final BigInteger NUMBER;
        private int power;

        public Holder(BigInteger n) {
            NUMBER = n;
            power = 1;
        }

        public BigInteger getValue() {
            return NUMBER.pow(power);
        }

        public void pInc() {
            power++;
        }

        public boolean isInt() {
            return NUMBER.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0;
        }

        public int getPower() {
            return power;
        }
    }

    public static ArrayList<Holder> factor(BigInteger n) {
        ArrayList<BigInteger> factorized = fac(n);
        ArrayList<Holder> res = new ArrayList<>();
        for (BigInteger b : factorized) {
            boolean flag = true;
            for (Holder h : res) {
                if (h.NUMBER.equals(b)) {
                    h.pInc();
                    flag = false;
                }
            }
            if (flag) {
                res.add(new Holder(b));
            }
        }
        return res;
    }

    private static ArrayList<BigInteger> fac(BigInteger n) {
        BigInteger bound = n.sqrt();
        if (bound.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0) {
            int b = bound.intValue();
            for (int i = 2; i < b; i++) {
                BigInteger test = n.mod(BigInteger.valueOf(i));
                if (test.equals(BigInteger.ZERO)) {
                    ArrayList<BigInteger> inter = new ArrayList<>();
                    inter.add(BigInteger.valueOf(i));
                    inter.addAll(fac(n.divide(BigInteger.valueOf(i))));
                    return inter;
                }
            }
        } else {
            for (BigInteger i = BigInteger.TWO; i.compareTo(bound) < 0; i = i.add(BigInteger.ONE)) {
                BigInteger test = n.mod(i);
                if (test.equals(BigInteger.ZERO)) {
                    ArrayList<BigInteger> inter = new ArrayList<>();
                    inter.add(i);
                    inter.addAll(fac(n.divide(i)));
                    return inter;
                }
            }
        }
        ArrayList<BigInteger> fin = new ArrayList<>();
        fin.add(n);
        return fin;
    }
}
