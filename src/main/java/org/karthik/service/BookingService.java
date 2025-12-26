package main.java.org.karthik.service;

import main.java.org.karthik.dto.Booking;
import main.java.org.karthik.dto.Taxi;

import java.util.ArrayList;
import java.util.List;

public class BookingService {
    List<Taxi> taxis = new ArrayList<>();
    int bookingIdCounter = 1;

    public BookingService(int n) {
        for (int i = 1; i <= n; i++) {
            taxis.add(new Taxi(i));
        }
    }

    private int calculateDistance(char from, char to) {
        return Math.abs(to - from);
    }

    private int calculateAmount(char from, char to) {
        int km = calculateDistance(from, to) * 15;
        if (km <= 5) return 100;
        return 100 + (km - 5) * 10;
    }

    public void bookTaxi(int customerId, char from, char to, int pickupTime) {
        List<Taxi> availableTaxis = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        for (Taxi taxi : taxis) {
            if (taxi.isFreeAt(pickupTime)) {
                int dist = calculateDistance(from, taxi.getCurrentPoint());
                if (dist < minDistance) {
                    availableTaxis.clear();
                    availableTaxis.add(taxi);
                    minDistance = dist;
                } else if (dist == minDistance) {
                    availableTaxis.add(taxi);
                }
            }
        }

        if (availableTaxis.isEmpty()) {
            System.out.println("No taxi can be allotted at this time.");
            return;
        }

        // Choose taxi with minimum earnings
        Taxi selected = availableTaxis.get(0);
        for (Taxi taxi : availableTaxis) {
            if (taxi.getTotalEarnings() < selected.getTotalEarnings()) {
                selected = taxi;
            }
        }

        int travelTime = calculateDistance(from, to); // 1 hour per hop
        int dropTime = pickupTime + travelTime;
        int amount = calculateAmount(from, to);

        Booking booking = new Booking(bookingIdCounter++, customerId, from, to, pickupTime, dropTime, amount);
        selected.addBooking(booking);

        System.out.println("Taxi can be allotted.");
        System.out.println("Taxi-" + selected.getId() + " is allotted");
    }

    public void displayTaxiDetails() {
        for (Taxi taxi : taxis) {
            System.out.println(taxi);
        }
    }

    public void displaySingleTaxiDetail(int taxiId) {
        for (Taxi taxi : taxis) {
            if (taxi.getId() == taxiId) {
                System.out.println(taxi);
                return;
            }
        }
        System.out.println("Taxi with ID " + taxiId + " not found.");
    }
}
