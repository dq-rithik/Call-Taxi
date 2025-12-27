package main.java.com.calltaxi;

import main.java.com.calltaxi.service.BookingService;

import java.util.Scanner;

public class CallTaxiApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Total Taxi's: ");
        int totalTaxi = scanner.nextInt();
        BookingService service = new BookingService(totalTaxi);

        while (true) {
            System.out.println("\n1. Book Taxi\n2. Display Taxi Details\n3. Display Single Taxi Detail\n4. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Customer ID: ");
                    int cid = scanner.nextInt();
                    System.out.print("Pickup Point (A-F): ");
                    char from = scanner.next().toUpperCase().charAt(0);
                    System.out.print("Drop Point (A-F): ");
                    char to = scanner.next().toUpperCase().charAt(0);
                    System.out.print("Pickup Time (hour): ");
                    int time = scanner.nextInt();
                    service.bookTaxi(cid, from, to, time);
                    break;
                case 2:
                    service.displayTaxiDetails();
                    break;
                case 3:
                    System.out.print("Taxi ID: ");
                    int taxiId = scanner.nextInt();
                    service.displaySingleTaxiDetail(taxiId);
                    break;
                case 4:
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
