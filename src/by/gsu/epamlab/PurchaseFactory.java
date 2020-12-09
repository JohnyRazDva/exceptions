package by.gsu.epamlab;

import exceptions.CsvLineException;

import java.util.Scanner;

public class PurchaseFactory {
    public final static String DELIMITER = ";";
    public final static int GENERAL_PURCHASE_ARGUMENTS_NUMBER = 3;
    public final static int PRICE_DISCOUNT_PURCHASE_ARGUMENTS_NUMBER = 4;

    public enum PurchaseTypes {
        GENERAL_PURCHASE {
            @Override
            Purchase getPurchase(String line) throws IllegalArgumentException {
                return new Purchase(line);
            }
        },
        PRICE_DISCOUNT_PURCHASE {
            @Override
            Purchase getPurchase(String line) throws IllegalArgumentException {
                return new PriceDiscountPurchase(line);
            }
        };

        abstract Purchase getPurchase(String line) throws IllegalArgumentException;
    }

    public static Purchase getPurchaseFromFactory(Scanner sc) throws IllegalArgumentException {
        String dataLineFromScanner = sc.nextLine();
        String[] array = dataLineFromScanner.split(DELIMITER);
        int numberOfArguments = array.length;
        if (numberOfArguments != GENERAL_PURCHASE_ARGUMENTS_NUMBER && numberOfArguments != PRICE_DISCOUNT_PURCHASE_ARGUMENTS_NUMBER) {
            throw new CsvLineException(dataLineFromScanner + " -> wrong number of arguments");
        }
        return (numberOfArguments == GENERAL_PURCHASE_ARGUMENTS_NUMBER)
                ? PurchaseTypes.GENERAL_PURCHASE.getPurchase(dataLineFromScanner) : PurchaseTypes.PRICE_DISCOUNT_PURCHASE.getPurchase(dataLineFromScanner);
    }
}