import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class HotelReservationSystem {
    public static void main(String[] args) {
        new ReservationSystem();
    }
}

class ReservationSystem {

    JFrame frame;
    JComboBox<String> roomTypeBox;
    JTextField nameField, contactField;
    JLabel resultLabel;
    JTextArea bookingsArea;

    Map<String, Integer> availableRooms = new HashMap<>();
    List<String> bookings = new ArrayList<>();

    public ReservationSystem() {
        frame = new JFrame("Hotel Reservation System");
        frame.setSize(700, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        availableRooms.put("Standard", 5);
        availableRooms.put("Deluxe", 3);
        availableRooms.put("Suite", 2);

        JLabel titleLabel = new JLabel("Hotel Reservation");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setBounds(230, 10, 300, 30);
        frame.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 100, 25);
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 60, 200, 25);
        frame.add(nameField);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setBounds(50, 100, 100, 25);
        frame.add(contactLabel);

        contactField = new JTextField();
        contactField.setBounds(150, 100, 200, 25);
        frame.add(contactField);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setBounds(50, 140, 100, 25);
        frame.add(roomTypeLabel);

        roomTypeBox = new JComboBox<>(new String[]{"Standard", "Deluxe", "Suite"});
        roomTypeBox.setBounds(150, 140, 200, 25);
        frame.add(roomTypeBox);

        JButton checkButton = new JButton("Check Availability");
        checkButton.setBounds(150, 180, 150, 30);
        frame.add(checkButton);

        JButton bookButton = new JButton("Book Now");
        bookButton.setBounds(310, 180, 120, 30);
        frame.add(bookButton);

        JButton viewBookings = new JButton("View Bookings");
        viewBookings.setBounds(440, 180, 150, 30);
        frame.add(viewBookings);

        resultLabel = new JLabel();
        resultLabel.setBounds(50, 230, 500, 30);
        frame.add(resultLabel);

        bookingsArea = new JTextArea();
        bookingsArea.setBounds(50, 270, 600, 350);
        bookingsArea.setEditable(false);
        frame.add(bookingsArea);

        // Event Listeners
        checkButton.addActionListener(e -> checkAvailability());
        bookButton.addActionListener(e -> makeBooking());
        viewBookings.addActionListener(e -> showBookings());

        frame.setVisible(true);
    }

    private void checkAvailability() {
        String roomType = (String) roomTypeBox.getSelectedItem();
        int count = availableRooms.getOrDefault(roomType, 0);
        resultLabel.setText("Available " + roomType + " Rooms: " + count);
    }

    private void makeBooking() {
        String name = nameField.getText().trim();
        String contact = contactField.getText().trim();
        String roomType = (String) roomTypeBox.getSelectedItem();

        if (name.isEmpty() || contact.isEmpty()) {
            resultLabel.setText("Please enter name and contact.");
            return;
        }

        int available = availableRooms.getOrDefault(roomType, 0);
        if (available <= 0) {
            resultLabel.setText("No " + roomType + " rooms available.");
            return;
        }

        // Simulate payment
        int confirm = JOptionPane.showConfirmDialog(frame, "Proceed to payment of $" + getRoomPrice(roomType) + "?", "Payment", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.NO_OPTION) {
            resultLabel.setText("Payment cancelled.");
            return;
        }

        // Booking successful
        availableRooms.put(roomType, available - 1);
        String bookingInfo = "Name: " + name + ", Contact: " + contact + ", Room: " + roomType;
        bookings.add(bookingInfo);
        resultLabel.setText("Booking successful!");
        clearFields();
    }

    private void showBookings() {
        if (bookings.isEmpty()) {
            bookingsArea.setText("No bookings yet.");
        } else {
            bookingsArea.setText("Booking Details:\n\n");
            for (String b : bookings) {
                bookingsArea.append(b + "\n");
            }
        }
    }

    private int getRoomPrice(String roomType) {
        switch (roomType) {
            case "Standard": return 100;
            case "Deluxe": return 200;
            case "Suite": return 300;
            default: return 0;
        }
    }

    private void clearFields() {
        nameField.setText("");
        contactField.setText("");
    }
          }
