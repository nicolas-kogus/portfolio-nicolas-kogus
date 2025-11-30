package application;

import entities.ImportedProducts;
import entities.Product;
import entities.UsedProducts;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
	static void main() throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Product commonProducts = new Product();

        System.out.print("Enter the number of products: ");
        int number = sc.nextInt();

        for (int i = 0; i < number; i++) {

            System.out.printf("Product #%d data:%n", i + 1);
            System.out.print("Common, used or imported (c/u/i)? ");
            char input = sc.next().charAt(0);

            if (input == 'c') {

                System.out.print("Name: ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.print("Price: ");
                double price = sc.nextDouble();

                Product product = new Product(name, price);
                commonProducts.addProduct(product);

            } else if (input == 'u') {

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                System.out.print("Name: ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.print("Price: ");
                double price = sc.nextDouble();
                System.out.print("Manufacture date (DD/MM/YYYY): ");
                String date = sc.next();
                Date manufactureDate = sdf.parse(date);

                Product product = new UsedProducts(name, price, manufactureDate);
                commonProducts.addProduct(product);

            } else {

                System.out.print("Name: ");
                sc.nextLine();
                String name = sc.nextLine();
                System.out.print("Price: ");
                double price = sc.nextDouble();
                System.out.print("Customs fee: ");
                double customsFee = sc.nextDouble();

                Product product = new ImportedProducts(name, price, customsFee);
                commonProducts.addProduct(product);

            }
        }

        sc.close();

        System.out.println();
        System.out.println("PRICE TAGS:");

        for (Product item : commonProducts.products) {

            System.out.println(item.priceTag());

        }

	}
}
