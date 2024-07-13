package org.main;


import org.config.UseServiceObj;
import org.service.inter.ItemInter;
import org.service.inter.ProductInter;
import org.service.inter.SaleInter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ProductInter useProductServiceObj = UseServiceObj.getProductServiceObj(args[0]);
        SaleInter useSaleServiceObj = UseServiceObj.getSaleServiceObj(args[0]);
        ItemInter useItemServiceObj = UseServiceObj.getItermServiceObj(args[0]);
        Scanner scanner = new Scanner(System.in);
        Operations operations = new Operations();

        boolean operation = true;
        while (operation) {
            System.out.println("1-product,2-sale,3-exit");
            int operationValue = scanner.nextInt();
            switch (operationValue) {
                case 1: {
                    if (useProductServiceObj != null) {
                        System.out.println("1-add,2-update,3-delete,4-show,5-show by price,6- by category,7-by name");
                        int firstCase = scanner.nextInt();
                        if (firstCase == 1) {
                            useProductServiceObj.addProduct(operations.addProduct());
                        } else if (firstCase == 2) {
                            operations.updateChooseCase(useProductServiceObj);
                        } else if (firstCase == 3) {
                            operations.deleteCase(useProductServiceObj);
                        } else if (firstCase == 4) {
                            operations.showProduct(useProductServiceObj);
                        } else if (firstCase == 5) {
                            operations.maxMinvalueCase(useProductServiceObj);
                        } else if (firstCase == 6) {
                            operations.showProductByCategory(useProductServiceObj);
                        } else if (firstCase == 7) {
                            operations.showProductByName(useProductServiceObj);
                        } else {
                            System.out.println("invalid operation");
                        }
                        break;
                    }
                }

                case 2: {
                    if (useSaleServiceObj != null) {
                        System.out.println("0-show,1-add,2-return item ,3-delete,select by 4- duration ,5-price,6-date,7-id");
                        int secondCase = scanner.nextInt();
                        if (secondCase == 1) {
                           useSaleServiceObj.addSale(operations.addSale(useItemServiceObj,useProductServiceObj));
                        } else if (secondCase == 0) {
                            operations.showSale(useSaleServiceObj);
                        } else if (secondCase == 2) {
                            operations.removeSaleItem(useSaleServiceObj);
                        } else if (secondCase == 3) {
                            operations.removeSale(useSaleServiceObj);
                        } else if (secondCase == 4) {
                            operations.showSaleBetweenDate(useSaleServiceObj);
                        } else if (secondCase == 5) {
                            operations.showSaleBetweenPrice(useSaleServiceObj);
                        } else if (secondCase == 6) {
                            operations.showProductByDate(useSaleServiceObj);
                        } else if (secondCase == 7) {
                            operations.getSaleById(useSaleServiceObj);
                        } else {
                            System.out.println("invalid operation");
                        }
                    }
                    break;
                }
                case 3: {
                    System.out.println("bye!!");
                    operation = false;
                    break;
                }
                default: {
                    System.out.println("invalid Operation value");
                }
            }

        }


    }
}