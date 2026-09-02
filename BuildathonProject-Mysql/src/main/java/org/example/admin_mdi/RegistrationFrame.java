package org.example.admin_mdi;


import org.example.db.ConnectWithDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.UUID;

public class RegistrationFrame extends JInternalFrame implements ActionListener {

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

    JComboBox<String> roleComboBox,branchComboBox;

    JButton saveButton;
    JButton clearButton;
    ConnectWithDB connect;

    public RegistrationFrame() {

        super("Student Registration", true, true, true, true);
        connect=new ConnectWithDB();
        setSize(500,450);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,500,450);
        panel.setBackground(Color.WHITE);
        add(panel);

        heading = new JLabel("Registration");
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

        nameField = new JTextField();
        nameField.setBounds(150,140,280,30);
        panel.add(nameField);

        roleLabel = new JLabel("Branch");
        roleLabel.setBounds(30,185,100,25);
        panel.add(roleLabel);

        roleComboBox = new JComboBox<>();
        roleComboBox.addItem("AI-ML");
        roleComboBox.addItem("CS");
        roleComboBox.addItem("EC");
        roleComboBox.addItem("CE");
        roleComboBox.addItem("AE");
        roleComboBox.setBounds(150,185,280,30);
        panel.add(roleComboBox);

        roleLabel = new JLabel("Semester");
//        roleLabel.setBounds(30,275,100,25);
        roleLabel.setBounds(30,230,100,25);
        panel.add(roleLabel);

        branchComboBox = new JComboBox<>();
        branchComboBox.addItem("I");
        branchComboBox.addItem("II");
        branchComboBox.addItem("III");
        branchComboBox.addItem("IV");
        branchComboBox.addItem("V");
        branchComboBox.addItem("VI");
        branchComboBox.addItem("VII");
        branchComboBox.addItem("VIII");
//        roleComboBox.setBounds(150,275,180,30);
        branchComboBox.setBounds(150,230,280,30);
        panel.add(branchComboBox);


        phoneLabel = new JLabel("Phone");
        phoneLabel.setBounds(30,275,100,25);
        panel.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150,275,180,30);
        panel.add(phoneField);


        statusLabel = new JLabel("");
        statusLabel.setBounds(30,320,400,25);
        statusLabel.setForeground(new Color(0,128,0));
        panel.add(statusLabel);

        saveButton = new JButton("Save");
        saveButton.setBounds(120,360,100,35);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(260,360,100,35);
        clearButton.addActionListener(this);
        panel.add(clearButton);

        roleComboBox.setSelectedIndex(-1);
        branchComboBox.setSelectedIndex(-1);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==saveButton){

            if(nameField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Name is required.");
                return;
            }

            if(phoneField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Phone number is required.");
                return;
            }

            statusLabel.setForeground(new Color(0,128,0));
            statusLabel.setText("Registration successfully.");

            try{
                String query="insert into user_master values(user_sq.nextval,?,?,?)";
                PreparedStatement preparedStatement=connect.con.prepareStatement(query);
                Random random=new Random();
                String username=nameField.getText().substring(0,3)+random.nextInt(100,9999);
                preparedStatement.setString(1,username);
                preparedStatement.setString(2,"Sagar@123");
                preparedStatement.setString(3,"STUDENT");
                preparedStatement.executeUpdate();

                query="select userId from user_master where username = ? and role='STUDENT'";
                PreparedStatement preparedStatement1=connect.con.prepareStatement(query);
                preparedStatement1.setString(1,username);
                ResultSet rs=preparedStatement1.executeQuery();
                if(rs.next()){
                    int userId=rs.getInt(1);
                    query="insert into student_master values(student_sq.nextval,?,?,?,?,?,?,?)";
                    PreparedStatement preparedStatement2=connect.con.prepareStatement(query);
                    preparedStatement2.setString(1,nameField.getText());
                    preparedStatement2.setString(2,"0608"+roleComboBox.getSelectedItem().toString()+"2310"+userId);
                    preparedStatement2.setString(3,roleComboBox.getSelectedItem().toString());
                    preparedStatement2.setString(4,branchComboBox.getSelectedItem().toString());
                    preparedStatement2.setString(5,"Nothing");
                    preparedStatement2.setInt(6,userId);
                    preparedStatement2.setString(7,phoneField.getText());
                    preparedStatement2.executeUpdate();
                    nameField.setText("");
                    statusLabel.setText("");
                    roleComboBox.setSelectedIndex(-1);
                    branchComboBox.setSelectedIndex(-1);
                    phoneField.setText("");
                }
                JOptionPane.showMessageDialog(this,"Registration has been successful","Registration",1);
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource()==clearButton){

            nameField.setText("");
            statusLabel.setText("");
            roleComboBox.setSelectedIndex(-1);
            branchComboBox.setSelectedIndex(-1);
            phoneField.setText("");
        }
    }
}

