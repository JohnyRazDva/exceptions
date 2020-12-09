package by.gsu.epamlab;

import java.util.Comparator;

public class    ComparePurchaseWithReflection implements Comparator<Purchase> {


    @Override
    public int compare(Purchase o1, Purchase o2) {
        if (!o1.getName().equals(o2.getName())) {
            return o1.getName().compareTo(o2.getName());
        } else {
            if (o1.getClass().isAssignableFrom(o2.getClass())) {
                return -1;
            } else if (o2.getClass().isAssignableFrom(o1.getClass())) {
                return 1;
            } else return 0;
        }
    }
}
