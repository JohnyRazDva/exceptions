package by.gsu.epamlab;

import by.gsu.epamlab.exceptions.NegativeValueException;
import by.gsu.epamlab.exceptions.NonPositiveValueException;

public class Byn implements Comparable<Byn> {
    private int coinValue;

    public Byn() {
    }

    public Byn(int coinValue) throws NegativeValueException {
        if (coinValue < 0) {
            throw new NegativeValueException();
        } else {
            this.coinValue = coinValue;
        }
    }

    public Byn(Byn byn) throws NegativeValueException {
        this(byn.coinValue);
    }


    public Byn(int rubs, int coin) throws NegativeValueException {
        this(rubs * 100 + coin);
    }

    @Override
    public String toString() {
        return coinValue / 100 + "." + (coinValue % 100) / 10 + (coinValue % 100) % 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Byn byn = (Byn) o;
        return coinValue == byn.coinValue;
    }

    @Override
    public int compareTo(Byn o) {
        return coinValue - o.coinValue;
    }

    public Byn add(Byn byn) {
        coinValue += byn.coinValue;
        return this;
    }

    public Byn sub(Byn byn) {
        coinValue -= byn.coinValue;
        return this;
    }

    public Byn mul(int factor) {
        coinValue *= factor;
        return this;
    }

    public Byn mul(double factor, RoundMethod roundMethod, int d) {
        coinValue = roundMethod.round(coinValue * factor, d);
        return this;
    }

    public Byn round(RoundMethod roundMethod, int d) {
        coinValue = roundMethod.round(coinValue, d);
        return this;
    }

    public enum RoundMethod {
        FLOOR {
            @Override
            double roundFunction(double value) {
                return Math.floor(value);
            }
        },
        ROUND {
            @Override
            double roundFunction(double value) {
                return Math.round(value);
            }
        },
        CEIL {
            @Override
            double roundFunction(double value) {
                return Math.ceil(value);
            }
        };

        abstract double roundFunction(double value);

        int round(double roundedValue, int d) {
            int tenPow = TEN_POW_D[d];
            int result = (int) (roundFunction(roundedValue / tenPow) * tenPow);
            return result;
        }

        private final static int[] TEN_POW_D = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000};
    }
}
