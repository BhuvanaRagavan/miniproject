 import java.sql.*;
import java.util.Scanner;

public class BusBookingApp {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/bus_booking";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Mysql";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("\n--- Bus Booking Application ---");
                System.out.println("1. Add Bus");
                System.out.println("2. View Available Buses");
                System.out.println("3. Book Ticket");
                System.out.println("4. View Bookings");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addBus(connection, scanner);
                        break;
                    case 2:
                        viewBuses(connection);
                        break;
                    case 3:
                        bookTicket(connection, scanner);
                        break;
                    case 4:
                        viewBookings(connection);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addBus(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter bus name: ");
        String busName = scanner.nextLine();
        System.out.print("Enter number of seats: ");
        int seats = scanner.nextInt();

        String sql = "INSERT INTO buses (bus_name, seats_available) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, busName);
            stmt.setInt(2, seats);
            stmt.executeUpdate();
            System.out.println("Bus added successfully.");
        }
    }

    private static void viewBuses(Connection connection) throws SQLException {
        String sql = "SELECT * FROM buses";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nAvailable Buses:");
            while (rs.next()) {
                System.out.println("Bus ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("bus_name") +
                        ", Seats Available: " + rs.getInt("seats_available"));
            }
        }
    }

    private static void bookTicket(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Enter Bus ID: ");
        int busId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter your name: ");
        String passengerName = scanner.nextLine();

        // Check seat availability
        String checkSql = "SELECT seats_available FROM buses WHERE id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
            checkStmt.setInt(1, busId);
            try (ResultSet rs = checkStmt.executeQuery()) {
                if (rs.next() && rs.getInt("seats_available") > 0) {
                    // Book ticket
                    connection.setAutoCommit(false);

                    String bookSql = "INSERT INTO bookings (bus_id, passenger_name) VALUES (?, ?)";
                    try (PreparedStatement bookStmt = connection.prepareStatement(bookSql)) {
                        bookStmt.setInt(1, busId);
                        bookStmt.setString(2, passengerName);
                        bookStmt.executeUpdate();
                    }

                    String updateSeatsSql = "UPDATE buses SET seats_available = seats_available - 1 WHERE id = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateSeatsSql)) {
                        updateStmt.setInt(1, busId);
                        updateStmt.executeUpdate();
                    }

                    connection.commit();
                    System.out.println("Ticket booked successfully.");
                } else {
                    System.out.println("No seats available for the selected bus.");
                }
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }
        }
    }

    private static void viewBookings(Connection connection) throws SQLException {
        String sql = "SELECT b.id AS booking_id, b.passenger_name, bu.bus_name " +
                     "FROM bookings b INNER JOIN buses bu ON b.bus_id = bu.id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nBookings:");
            while (rs.next()) {
                System.out.println("Booking ID: " + rs.getInt("booking_id") +
                        ", Passenger Name: " + rs.getString("passenger_name") +
                        ", Bus Name: " + rs.getString("bus_name"));
            }
        }
    }
}
