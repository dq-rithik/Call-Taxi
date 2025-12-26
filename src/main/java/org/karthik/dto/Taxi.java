package main.java.org.karthik.dto;

import java.util.ArrayList;

public class Taxi {
    private int id;
    private double totalEarnings;
    private int freeTime;
    private char currentPoint;
    private ArrayList<Booking> bookings = new ArrayList<>();

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Taxi(int id) {
        this.id = id;
        this.totalEarnings = 0;
        this.freeTime = 0;
        this.currentPoint = 'A';
    }

    public boolean isFreeAt(int time) {
        return freeTime <= time;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        totalEarnings += booking.getAmount();
        freeTime = booking.getDropTime();
        currentPoint = booking.getTo();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Taxi-" + id + " Total Earnings: Rs." + totalEarnings + "\n");
        sb.append("BookingID CustomerID From To PickupTime DropTime Amount\n");
        for (Booking b : bookings) {
            sb.append(b).append("\n");
        }
        return sb.toString();
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public char getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(char currentPoint) {
        this.currentPoint = currentPoint;
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(ArrayList<Booking> bookings) {
        this.bookings = bookings;
    }
}
