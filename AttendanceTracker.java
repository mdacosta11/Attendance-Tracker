import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class AttendanceTracker {
    // Counters
    private int adultCount = 0;
    private int seniorCount = 0;
    private int childCount = 0;
    private int hour = 1;

    private ArrayList<String> hourLog = new ArrayList<>();

    // UI Elements
    private JLabel adultLabel, seniorLabel, childLabel, totalLabel, hourLabel;
    private JTextArea logArea;

    public AttendanceTracker() {
        JFrame frame = new JFrame("Swim Check-In Counter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new BorderLayout());

        // Top Panel for Count Display
        JPanel countPanel = new JPanel(new GridLayout(5, 1));
        hourLabel = new JLabel("Hour: 1");
        adultLabel = new JLabel("Adults: 0");
        seniorLabel = new JLabel("Seniors: 0");
        childLabel = new JLabel("Children: 0");
        totalLabel = new JLabel("Total: 0");

        countPanel.add(hourLabel);
        countPanel.add(adultLabel);
        countPanel.add(seniorLabel);
        countPanel.add(childLabel);
        countPanel.add(totalLabel);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 3));
        JButton adultBtn = new JButton("Add Adult");
        JButton seniorBtn = new JButton("Add Senior");
        JButton childBtn = new JButton("Add Child");
        JButton saveHourBtn = new JButton("Save Hour");
        JButton resetBtn = new JButton("Reset");
        JButton exitBtn = new JButton("Exit");

        buttonPanel.add(adultBtn);
        buttonPanel.add(seniorBtn);
        buttonPanel.add(childBtn);
        buttonPanel.add(saveHourBtn);
        buttonPanel.add(resetBtn);
        buttonPanel.add(exitBtn);

        // Log area
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);

        // Add components to frame
        frame.add(countPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.add(scrollPane, BorderLayout.SOUTH);

        // Action Listeners
        adultBtn.addActionListener(e -> {
            adultCount++;
            updateDisplay();
        });

        seniorBtn.addActionListener(e -> {
            seniorCount++;
            updateDisplay();
        });

        childBtn.addActionListener(e -> {
            childCount++;
            updateDisplay();
        });

        resetBtn.addActionListener(e -> {
            adultCount = 0;
            seniorCount = 0;
            childCount = 0;
            updateDisplay();
        });

        saveHourBtn.addActionListener(e -> {
            int total = adultCount + seniorCount + childCount;
            String summary = "Hour " + hour + " - Adults: " + adultCount + " | Seniors: " + seniorCount + " | Children: " + childCount + " | Total: " + total;
            hourLog.add(summary);
            logArea.append(summary + "\n");

            // Reset counters and update
            adultCount = 0;
            seniorCount = 0;
            childCount = 0;
            hour++;
            updateDisplay();
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Goodbye!");
            System.exit(0);
        });

        frame.setVisible(true);
    }

    // Update label values
    private void updateDisplay() {
        int total = adultCount + seniorCount + childCount;
        hourLabel.setText("Hour: " + hour);
        adultLabel.setText("Adults: " + adultCount);
        seniorLabel.setText("Seniors: " + seniorCount);
        childLabel.setText("Children: " + childCount);
        totalLabel.setText("Total: " + total);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AttendanceTracker::new);
    }
}
