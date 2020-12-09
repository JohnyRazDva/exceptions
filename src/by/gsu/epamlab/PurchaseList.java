package by.gsu.epamlab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PurchaseList {
    public List<Purchase> list;

    public PurchaseList() {
    }

    public PurchaseList(String DataFileName) {
        Scanner sc = null;
        try {
            sc = new Scanner(new FileReader(DataFileName));
            list = new ArrayList<Purchase>();
            while (sc.hasNextLine()) {
                try {
                    list.add(PurchaseFactory.getPurchaseFromFactory(sc));
                } catch (IllegalArgumentException e) {
                    System.err.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

    public void insertPurchase(Purchase purchase, int index) {
        try {
            list.add(index, purchase);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index - " + index + " purchase add to the end of the list");
            list.add(purchase);
        }
    }

    public void deletePurchase(int index) {
        try {
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid index - " + index);
        }
    }

    public Byn getTotalCostOfAllPurchases() {
        Byn cost = new Byn(0);
        for (Purchase purchase : list) {
            cost.add(purchase.getCost());
        }
        return cost;
    }

    public void printList() {
        System.out.printf("  %s      %10s      %10s      %10s      %10s", "Name", "Price", "Number", "Discount", "Cost" + "\n");
        for (Purchase purchase : list) {
            String discount = "-";
            if (purchase instanceof PriceDiscountPurchase) {
                discount = ((PriceDiscountPurchase) purchase).getDiscount().toString();
            }
            System.out.printf("%-11s %10s %15d %15s %15s", purchase.getName(), purchase.getPrice(), purchase.getNumber(), discount, purchase.getCost() + "\n");
        }
        System.out.printf("Total cost %58s" + "\n", getTotalCostOfAllPurchases());
    }
}
