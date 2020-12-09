package by.gsu.epamlab;

import by.gsu.epamlab.exceptions.IncorrectDataException;
import by.gsu.epamlab.exceptions.NegativeValueException;
import by.gsu.epamlab.exceptions.NonPositiveValueException;

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

    public Purchase(Scanner sc) throws IllegalArgumentException {
        String line = sc.nextLine();
        String[] array = line.split(PurchaseFactory.DELIMITER);
        this.name = array[NAME_INDEX];
        this.price = new Byn(Integer.parseInt(array[PRICE_INDEX]));
        setNumber(Integer.parseInt(array[NUMBER_INDEX]));
    }

    public Purchase(String line) throws IllegalArgumentException {
        String[] array = line.split(PurchaseFactory.DELIMITER);
        String name = array[NAME_INDEX];
        Byn price;
        int number;
        try {
            price = new Byn(Integer.parseInt(array[PRICE_INDEX]));
            number = Integer.parseInt(array[NUMBER_INDEX]);
        } catch (NegativeValueException e) {
            throw new IncorrectDataException(line + " -> negative value in price " + PRICE_INDEX);
        } catch (NumberFormatException e) {
            throw new IncorrectDataException(line + " -> NumberFormatException in price or number of purchase");
        }
        if (name.equals("")) {
            throw new IncorrectDataException(line + " -> empty name");
        } else {
            this.name = name;
        }
        if (price.compareTo(new Byn(0)) <= 0) {
            throw new NonPositiveValueException(line + " -> non positive value " + price + " in price");
        } else {
            this.price = price;
        }
        if (number <= 0) {
            throw new NonPositiveValueException(line + " -> non positive value " + number + " in number");
        } else {
            setNumber(number);
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

    public void setName(String name) {
        
        this.name = name;
    }

    public  void setPrice(Byn price) {
        this.price = price;
    }

    public final void setNumber(int number) {
        if (number < 0){
            throw new NonPositiveValueException("Non positive value in number of purchase");
        }
        this.number = number;
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
