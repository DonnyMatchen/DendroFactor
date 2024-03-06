package com.donny.dendrofactor;

import java.math.BigInteger;
import java.util.ArrayList;

public class DendroFactor {
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

        @Override
        public String toString() {
            return "(" + NUMBER + "^" + power + ")";
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
                res.add(0, new Holder(b));
            }
        }
        return res;
    }

    private static ArrayList<BigInteger> fac(BigInteger n) {
        BigInteger candidate = BigInteger.TWO;
        if (!Primes.contains(n)) {
            while (candidate.pow(2).compareTo(n) <= 0) {
                BigInteger test = n.mod(candidate);
                if (test.compareTo(BigInteger.ZERO) == 0) {
                    if (!Primes.contains(candidate)) {
                        Primes.addPrime(candidate);
                    }
                    ArrayList<BigInteger> fin = fac(n.divide(candidate));
                    fin.add(candidate);
                    return fin;
                } else {
                    candidate = Primes.nextPrime(candidate);
                }
            }
            Primes.addPrime(n);
        }
        ArrayList<BigInteger> fin = new ArrayList<>();
        fin.add(n);
        return fin;
    }
}
