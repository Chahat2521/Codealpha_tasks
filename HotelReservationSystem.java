import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelReservationSystem {
    private List<Room> rooms;
    private List<Booking> bookings;

    public HotelReservationSystem() {
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();

        // Initialize rooms
        rooms.add(new Room("Single", 1, 100.0));
        rooms.add(new Room("Double", 2, 150.0));
        rooms.add(new Room("Suite", 3, 250.0));
    }

    public void searchRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println(room.toString());
            }
        }
    }

    public void makeReservation() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter room type: ");
        String roomType = scanner.nextLine();
        System.out.print("Enter number of guests: ");
        int numGuests = scanner.nextInt();
        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkInDate = scanner.next();
        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOutDate = scanner.next();

        Room room = findRoom(roomType, numGuests);
        if (room != null) {
            Booking booking = new Booking(room, checkInDate, checkOutDate);
            bookings.add(booking);
            room.setBooked(true);
            System.out.println("Reservation successful!");
        } else {
            System.out.println("No available rooms found.");
        }
    }

    public void viewBookings() {
        System.out.println("Bookings:");
        for (Booking booking : bookings) {
            System.out.println(booking.toString());
        }
    }

    private Room findRoom(String roomType, int numGuests) {
        for (Room room : rooms) {
            if (room.getType().equals(roomType) && room.getCapacity() >= numGuests && !room.isBooked()) {
                return room;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        HotelReservationSystem system = new HotelReservationSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Search Rooms");
            System.out.println("2. Make Reservation");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.searchRooms();
                    break;
                case 2:
                    system.makeReservation();
                    break;
                case 3:
                    system.viewBookings();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

class Room {
    private String type;
    private int capacity;
    private double rate;
    private boolean booked;

    public Room(String type, int capacity, double rate) {
        this.type = type;
        this.capacity = capacity;
        this.rate = rate;
        this.booked = false;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRate() {
        return rate;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    @Override
    public String toString() {
        return "Room{" +
                "type='" + type + '\'' +
                ", capacity=" + capacity +
                ", rate=" + rate +
                '}';
    }
}

class Booking {
    private Room room;
    private String checkInDate;
    private String checkOutDate;

    public Booking(Room room, String checkInDate, String checkOutDate) {
        this.room = room;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "room=" + room +
                ", checkInDate='" + checkInDate + '\'' +
                ", checkOutDate='" + checkOutDate + '\'' +
                '}';
    }
}