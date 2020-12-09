package by.gsu.epamlab;

import by.gsu.epamlab.exceptions.*;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private final int DISCOUNT_INDEX = 3;
    private Byn discount;


    public PriceDiscountPurchase(String name, Byn price, int number, Byn discount) throws IllegalArgumentException {
        super(name, price, number);
        this.discount = new Byn(discount);
    }

    public PriceDiscountPurchase(String name, int price, int number, int discount) throws IllegalArgumentException {
        super(name, price, number);
        this.discount = new Byn(discount);
    }

    public PriceDiscountPurchase(Scanner sc) throws IllegalArgumentException {
        super(sc);
        String[] array = sc.next().split(PurchaseFactory.DELIMITER);
        this.discount = new Byn(Integer.parseInt(array[DISCOUNT_INDEX]));
    }

    public PriceDiscountPurchase(String line) throws IncorrectDataException {
        super(line);
        String[] array = line.split(PurchaseFactory.DELIMITER);
        Byn discount;
        try {
            discount = new Byn(Integer.parseInt(array[DISCOUNT_INDEX]));
        } catch (NegativeValueException e) {
            throw new IncorrectDataException(line + " -> negative value " + array[DISCOUNT_INDEX] + " in discount");
        } catch (NumberFormatException e) {
            throw new IncorrectDataException(line + " -> NumberFormatException in discount");
        }
        if (discount.compareTo(new Byn(0)) < 1) {
            throw new NonPositiveValueException(line + " -> non positive value " + array[DISCOUNT_INDEX] + " in discount");
        } else if (discount.compareTo(getPrice()) >= 0) {
            throw new IncorrectDiscountValue(line + " -> Discount " + array[DISCOUNT_INDEX] + " is more or equal to price ");
        } else {
            this.discount = discount;
        }
    }

    public Byn getDiscount() {
        return discount;
    }

    public void setDiscount(Byn discount) {
        this.discount = discount;
    }

    @Override
    public Byn getCost() {
        Byn cost = new Byn(getPrice());
        cost.sub(discount);
        cost.mul(getNumber());
        return cost;
    }

    @Override
    protected String fieldsToString() {
        return super.fieldsToString() + ";" + discount;
    }
}
