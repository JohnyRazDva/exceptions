import by.gsu.epamlab.PurchaseList;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            String mainCsvFileName = sc.nextLine();
            String secondCsvFileName = sc.nextLine();
            String comparatorName = sc.nextLine();
            PurchaseList purchaseList = new PurchaseList(mainCsvFileName);
            purchaseList.printList();
            PurchaseList purchaseList2 = new PurchaseList(secondCsvFileName);
            purchaseList.insertPurchase(purchaseList2.list.get(purchaseList2.list.size() - 1), 0);
            purchaseList.insertPurchase(purchaseList2.list.get(0), 1000);
            purchaseList.insertPurchase(purchaseList2.list.get(2), 2);
            purchaseList.deletePurchase(3);
            purchaseList.deletePurchase(10);
            purchaseList.deletePurchase(5);
            System.out.println();
            purchaseList.printList();
            Class c;
            Comparator comparator = null;
            try {
                c = Class.forName(comparatorName);
                comparator = (Comparator) c.newInstance();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            Collections.sort(purchaseList.list, comparator);
            System.out.println();
            purchaseList.printList();

            String foundedOrNo1 = "isn't found";
            String foundedOrNo2 = "isn't found";
            int index1 = Collections.binarySearch(purchaseList.list, purchaseList2.list.get(1), comparator);
            if (index1 >= 0) {
                foundedOrNo1 = "is found at position " + index1;
            }
            int index3 = Collections.binarySearch(purchaseList.list, purchaseList2.list.get(3), comparator);
            if (index3 >= 0) {
                foundedOrNo2 = "is found at position " + index3;
            }
            System.out.println("search results:");
            System.out.printf("Purchase %s %s" + "\n", purchaseList2.list.get(1), foundedOrNo1);
            System.out.printf("Purchase %s %s", purchaseList2.list.get(3), foundedOrNo2);

        }

}
