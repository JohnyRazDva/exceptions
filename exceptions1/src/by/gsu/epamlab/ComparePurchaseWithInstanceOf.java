package by.gsu.epamlab;

import java.util.Comparator;

public class ComparePurchaseWithInstanceOf implements Comparator<Purchase> {
    @Override
    public int compare(Purchase o1, Purchase o2) {
        int o1Num = 1, o2Num = 1;
        if (o1 instanceof PriceDiscountPurchase) {
            o1Num = 0;
        }
        if (o2 instanceof PriceDiscountPurchase) {
            o2Num = 0;
        }
        if (!o1.getName().equals(o2.getName())) {
            return o1.getName().compareTo(o2.getName());
        } else {
            return o2Num - o1Num;
        }
    }
}
