package javaadvance;

import java.awt.*;
import java.util.Arrays;
import javax.swing.*;

public class StudentRegistrationForm {
    public static void main(String[] args) {
        new RegistrationForm();
    }
}

class RegistrationForm {

    JTextField nameField, rollField, mathsField, engField, sciField;
    JLabel resultLabel;

    public RegistrationForm() {
        JFrame frame = new JFrame("Student Registration Form");
        frame.setSize(700, 700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JLabel titleLabel = new JLabel("Student Registration");
        titleLabel.setBounds(110, 10, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 60, 100, 25);
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(160, 60, 200, 25);
        frame.add(nameField);

        JLabel rollLabel = new JLabel("Roll No:");
        rollLabel.setBounds(50, 100, 100, 25);
        frame.add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(160, 100, 200, 25);
        frame.add(rollField);

        JLabel mathsLabel = new JLabel("Maths Marks:");
        mathsLabel.setBounds(50, 140, 100, 25);
        frame.add(mathsLabel);

        mathsField = new JTextField();
        mathsField.setBounds(160, 140, 200, 25);
        frame.add(mathsField);

        JLabel engLabel = new JLabel("English Marks:");
        engLabel.setBounds(50, 180, 100, 25);
        frame.add(engLabel);

        engField = new JTextField();
        engField.setBounds(160, 180, 200, 25);
        frame.add(engField);

        JLabel sciLabel = new JLabel("Science Marks:");
        sciLabel.setBounds(50, 220, 100, 25);
        frame.add(sciLabel);

        sciField = new JTextField();
        sciField.setBounds(160, 220, 200, 25);
        frame.add(sciField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(160, 260, 100, 30);
        frame.add(submitButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(270, 260, 100, 30);
        frame.add(clearButton);

        resultLabel = new JLabel();
        resultLabel.setBounds(30, 310, 600, 300); // Increased height
        resultLabel.setVerticalAlignment(JLabel.TOP);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(resultLabel);

        submitButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String roll = rollField.getText().trim();
                int maths = Integer.parseInt(mathsField.getText().trim());
                int eng = Integer.parseInt(engField.getText().trim());
                int sci = Integer.parseInt(sciField.getText().trim());

                if (name.isEmpty() || roll.isEmpty()) {
                    resultLabel.setText("Please fill in all fields.");
                    return;
                }

                int[] marks = {maths, eng, sci};
                int total = maths + eng + sci;
                double average = total / 3.0;
                int highest = Arrays.stream(marks).max().getAsInt();
                int lowest = Arrays.stream(marks).min().getAsInt(); // ✅ Calculate lowest

                resultLabel.setText("<html><b>Registered:</b> " + name + " (Roll No: " + roll + ")<br>"
                        + "Maths: " + maths + "<br>"
                        + "English: " + eng + "<br>"
                        + "Science: " + sci + "<br><br>"
                        + "<b>Total Marks:</b> " + total + "<br>"
                        + "<b>Average Marks:</b> " + String.format("%.2f", average) + "<br>"
                        + "<b>Highest Marks:</b> " + highest + "<br>"
                        + "<b>Lowest Marks:</b> " + lowest + "<br></html>"); // ✅ Ensure shown

            } catch (NumberFormatException ex) {
                resultLabel.setText("Please enter valid numbers for marks.");
            }
        });

        clearButton.addActionListener(e -> {
            nameField.setText("");
            rollField.setText("");
            mathsField.setText("");
            engField.setText("");
            sciField.setText("");
            resultLabel.setText("");
        });

        frame.setVisible(true);
    }
  }
