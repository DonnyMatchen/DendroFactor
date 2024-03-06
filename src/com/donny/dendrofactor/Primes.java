package com.donny.dendrofactor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;

public class Primes {
    private static final ArrayList<BigInteger> PRIMES = new ArrayList<>();

    public static boolean contains(BigInteger p) {
        return PRIMES.contains(p);
    }

    public static void addPrime(BigInteger p) {
        PRIMES.add(p);
        PRIMES.sort(Comparator.naturalOrder());
    }

    public static boolean deletePrime(BigInteger p) {
        return PRIMES.remove(p);
    }

    public static ArrayList<BigInteger> getPrimes() {
        return new ArrayList<>(PRIMES);
    }
}
