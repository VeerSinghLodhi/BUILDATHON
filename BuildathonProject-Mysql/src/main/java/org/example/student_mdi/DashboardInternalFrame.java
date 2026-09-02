package org.example.student_mdi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DashboardInternalFrame extends JInternalFrame {

    JPanel panel;

    JLabel heading;

    JPanel card1, card2, card3, card4;

    JLabel totalOrderValue, revenueValue, activeUserValue, pendingTaskValue;
    JLabel totalOrderText, revenueText, activeUserText, pendingTaskText;

    public DashboardInternalFrame() {

        super("Dashboard", true, true, true, true);

        setSize(700, 450);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 700, 450);
        panel.setBackground(new Color(245, 245, 245));
        add(panel);

        heading = new JLabel("Welcome Back!");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(20, 20, 250, 30);
        panel.add(heading);

        card1 = createCard();
        card1.setBounds(20, 80, 280, 120);

        totalOrderValue = createValueLabel("75%");
        totalOrderValue.setBounds(20, 20, 150, 30);

        totalOrderText = createTextLabel("Attendance");
        totalOrderText.setBounds(20, 60, 150, 20);

        card1.add(totalOrderValue);
        card1.add(totalOrderText);

        panel.add(card1);

        card2 = createCard();
        card2.setBounds(340, 80, 280, 120);

        revenueValue = createValueLabel("4");
        revenueValue.setBounds(20, 20, 180, 30);

        revenueText = createTextLabel("Total Assignments");
        revenueText.setBounds(20, 60, 200, 20);

        card2.add(revenueValue);
        card2.add(revenueText);

        panel.add(card2);

        card3 = createCard();
        card3.setBounds(20, 230, 280, 120);

        activeUserValue = createValueLabel("2");
        activeUserValue.setBounds(20, 20, 200, 30);

        activeUserText = createTextLabel("Submitted Assignments");
        activeUserText.setBounds(20, 60, 150, 20);

        card3.add(activeUserValue);
        card3.add(activeUserText);

        panel.add(card3);

        card4 = createCard();
        card4.setBounds(340, 230, 280, 120);

        pendingTaskValue = createValueLabel("2");
        pendingTaskValue.setBounds(20, 20, 150, 30);

        pendingTaskText = createTextLabel("Pending Assignment(s)");
        pendingTaskText.setBounds(20, 60, 200, 20);

        card4.add(pendingTaskValue);
        card4.add(pendingTaskText);

        panel.add(card4);
    }

    private JPanel createCard() {

        JPanel card = new JPanel();

        card.setLayout(null);
        card.setBackground(Color.WHITE);
        card.setBorder(new LineBorder(Color.LIGHT_GRAY));

        return card;
    }

    private JLabel createValueLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Arial", Font.BOLD, 22));

        return label;
    }

    private JLabel createTextLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setForeground(Color.GRAY);

        return label;
    }
}