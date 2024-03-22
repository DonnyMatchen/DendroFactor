package com.donny.dendrofactor;

import java.math.BigInteger;
import java.util.ArrayList;

public class DendroFactor {
    public static class Holder {
        private final BigInteger NUMBER;
        private BigInteger power;

        public Holder(BigInteger n) {
            NUMBER = n;
            power = BigInteger.ONE;
        }

        public BigInteger getValue() {
            return Functions.exp(NUMBER, power);
        }

        public void pInc() {
            power = power.add(BigInteger.ONE);
        }

        public boolean isInt() {
            return NUMBER.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) <= 0;
        }

        public BigInteger getNumber() {
            return NUMBER;
        }

        public BigInteger getPower() {
            return power;
        }

        @Override
        public String toString() {
            return "(" + NUMBER + "^" + power + ")";
        }
    }

    public static ArrayList<Holder> factor(BigInteger n) {
        BigInteger candidate = BigInteger.TWO;
        while (candidate.pow(2).compareTo(n) <= 0) {
            BigInteger test = n.mod(candidate);
            if (test.compareTo(BigInteger.ZERO) == 0) {
                ArrayList<Holder> fin = factor(n.divide(candidate));
                boolean found = false;
                for (Holder h : fin) {
                    if (h.NUMBER.compareTo(candidate) == 0) {
                        h.pInc();
                        found = true;
                    }
                }
                if (!found) {
                    fin.add(new Holder(candidate));
                }
                return fin;
            } else {
                candidate = candidate.add(BigInteger.ONE);
            }
        }
        ArrayList<Holder> fin = new ArrayList<>();
        fin.add(new Holder(n));
        return fin;
    }
}
