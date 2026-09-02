package org.example.student_mdi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileInternalFrame extends JInternalFrame implements ActionListener {

    JPanel panel;

    JLabel heading;
    JLabel avatarLabel;

    JLabel nameLabel;
    JLabel emailLabel;
    JLabel phoneLabel;
    JLabel roleLabel;
    JLabel statusLabel;

    JTextField nameField;
    JTextField emailField;
    JTextField phoneField;

    JComboBox<String> roleComboBox;

    JButton saveButton;
    JButton clearButton;

    public ProfileInternalFrame() {

        super("Profile", true, true, true, true);

        setSize(500,450);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,500,450);
        panel.setBackground(Color.WHITE);
        add(panel);

        heading = new JLabel("My Profile");
        heading.setFont(new Font("Arial", Font.BOLD,22));
        heading.setBounds(20,20,200,30);
        panel.add(heading);

        avatarLabel = new JLabel("👤");
        avatarLabel.setFont(new Font("Segoe UI Emoji",Font.PLAIN,48));
        avatarLabel.setBounds(210,55,60,60);
        panel.add(avatarLabel);

        nameLabel = new JLabel("Full Name");
        nameLabel.setBounds(30,140,100,25);
        panel.add(nameLabel);

        nameField = new JTextField("");
        nameField.setBounds(150,140,280,30);
        panel.add(nameField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(30,185,100,25);
        panel.add(emailLabel);

        emailField = new JTextField("");
        emailField.setBounds(150,185,280,30);
        panel.add(emailField);

        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(30,230,100,25);
        panel.add(phoneLabel);

        phoneField = new JTextField("");
        phoneField.setBounds(150,230,280,30);
        panel.add(phoneField);

//        roleLabel = new JLabel("Role");
//        roleLabel.setBounds(30,275,100,25);
//        panel.add(roleLabel);
//
//        roleComboBox = new JComboBox<>();
//        roleComboBox.addItem("Admin");
//        roleComboBox.addItem("Manager");
//        roleComboBox.addItem("Staff");
//        roleComboBox.setBounds(150,275,180,30);
//        panel.add(roleComboBox);

        statusLabel = new JLabel("");
        statusLabel.setBounds(30,320,400,25);
        statusLabel.setForeground(new Color(0,128,0));
        panel.add(statusLabel);

        saveButton = new JButton("Update");
        saveButton.setBounds(120,360,100,35);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(260,360,100,35);
        clearButton.addActionListener(this);
        panel.add(clearButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==saveButton){

            if(nameField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Name is required.");
                return;
            }

            if(emailField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Email is required.");
                return;
            }

            if(phoneField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Phone number is required.");
                return;
            }

            statusLabel.setForeground(new Color(0,128,0));
            statusLabel.setText("Profile updated successfully.");

            // Later:
            // Update Profile in Database using JDBC
        }

        if(e.getSource()==clearButton){

            nameField.setText("");
            emailField.setText("");
            phoneField.setText("");
            roleComboBox.setSelectedIndex(0);
            statusLabel.setText("");
        }
    }
}