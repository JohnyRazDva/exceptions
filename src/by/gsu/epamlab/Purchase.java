package by.gsu.epamlab;

import exceptions.NonPositiveArgument;

import java.util.Scanner;

public class Purchase implements Comparable<Purchase> {
    private final int NAME_INDEX = 0;
    private final int PRICE_INDEX = 1;
    private final int NUMBER_INDEX = 2;
    private String name;
    private Byn price;
    private int number;

    public Purchase() {
    }

    public Purchase(String name, Byn price, int number) {
        this.name = name;
        this.price = price;
        setNumber(number);
    }

    public Purchase(String name, int price, int number) throws IllegalArgumentException {
        this(name, new Byn(price), number);
    }



    public Purchase(String purchaseDataLine) throws IllegalArgumentException {
        String[] array = purchaseDataLine.split(PurchaseFactory.DELIMITER);
        try {
            setName(array[NAME_INDEX]);
            setPrice(new Byn(Integer.parseInt(array[PRICE_INDEX])));
            setNumber(Integer.parseInt(array[NUMBER_INDEX]));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(purchaseDataLine + "\n" + e.getMessage());
        }
    }
    public Purchase(Scanner sc) throws IllegalArgumentException {
        this(sc.nextLine());
    }


    public void setNumber(int number) {
        if (number < 0) {
            throw new NonPositiveArgument("Negative value in number of purchase - " + number, number);
        }
        this.number = number;
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("Empty name");
        }
        this.name = name;
    }

    public final void setPrice(Byn price) {
        if (price.compareTo(new Byn(0)) <= 0) {
            throw new IllegalArgumentException("Non positive value in price - " + price);
        } else {
            this.price = price;
        }
    }

    public String getName() {
        return name;
    }

    public Byn getPrice() {
        return price;
    }

    public int getNumber() {
        return number;
    }

    public Byn getCost() {
        Byn cost = new Byn(price);
        cost.mul(number);
        return cost;
    }

    protected String fieldsToString() {
        return name + ";" + price + ";" + number;
    }

    @Override
    public String toString() {
        return fieldsToString() + ";" + getCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Purchase)) return false;
        Purchase purchase = (Purchase) o;
        return (name.equals(purchase.name)) && (price.equals(purchase.price));
    }

    @Override
    public int compareTo(Purchase o) {
        return name.compareTo(o.getName());
    }


}
