package by.gsu.epamlab;

import java.util.Scanner;

public class PriceDiscountPurchase extends Purchase {
    private final int DISCOUNT_INDEX = 3;
    private Byn discount;

    public PriceDiscountPurchase(String name, Byn price, int number, Byn discount) throws IllegalArgumentException {
        super(name, price, number);
        setDiscount(discount);
    }

    public PriceDiscountPurchase(String name, Byn price, int number, int discount) throws IllegalArgumentException {
        super(name, price, number);
        setDiscount(new Byn(discount));
    }

    public PriceDiscountPurchase(String purchaseDataLine) throws IllegalArgumentException {
        super(purchaseDataLine);
        String[] array = purchaseDataLine.split(PurchaseFactory.DELIMITER);
        try {
            setDiscount(new Byn(Integer.parseInt(array[DISCOUNT_INDEX])));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(purchaseDataLine + "\n" + e.getMessage());
        }
    }

    public PriceDiscountPurchase(Scanner sc) throws IllegalArgumentException {
        this(sc.nextLine());
    }


    public final void setDiscount(Byn discount) {
        if (discount.compareTo(new Byn(0)) <= 0) {
            throw new IllegalArgumentException("Non positive value in discount - " + discount);
        }
        if (discount.compareTo(this.getPrice()) >= 0) {
            throw new IllegalArgumentException("Discount value can't be equals or more than price" + discount);
        }
        this.discount = discount;
    }

    public Byn getDiscount() {
        return discount;
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
