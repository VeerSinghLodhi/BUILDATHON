package org.example.faculty_mdi;


import org.example.db.ConnectWithDB;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AddMinorProjectFrame extends JInternalFrame implements ActionListener {

    JPanel panel;

    JLabel heading;
    JLabel avatarLabel;

    JLabel nameLabel,descriptionLabel;
    JLabel emailLabel;
    JLabel phoneLabel;
    JLabel roleLabel;
    JLabel statusLabel;

    JTextField nameField;
    JTextField emailField;
    JTextField phoneField;
    JTextArea descriptionField;

//    JComboBox<String> roleComboBox;

    JButton saveButton;
    JButton clearButton;

    String username;
    ConnectWithDB connect;
    public AddMinorProjectFrame(String username) {

        super("Assignment", true, true, true, true);
        connect=new ConnectWithDB();
        this.username=username;
        setSize(500,450);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,500,450);
        panel.setBackground(Color.WHITE);
        add(panel);

        heading = new JLabel("Add Minor Project");
        heading.setFont(new Font("Arial", Font.BOLD,22));
        heading.setBounds(20,20,200,30);
        panel.add(heading);

//        avatarLabel = new JLabel("👤");
//        avatarLabel.setFont(new Font("Segoe UI Emoji",Font.PLAIN,48));
//        avatarLabel.setBounds(210,55,60,60);
//        panel.add(avatarLabel);

        nameLabel = new JLabel("Project Title");
        nameLabel.setBounds(30,55,100,25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150,55,280,30);
        panel.add(nameField);

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(30,100,100,25);
        panel.add(descriptionLabel);

        descriptionField = new JTextArea("");
        descriptionField.setBounds(150,100,280,70);
        descriptionField.setBorder(new LineBorder(Color.BLACK));
        panel.add(descriptionField);

        emailLabel = new JLabel("Total Marks");
        emailLabel.setBounds(30,185,100,25);
        panel.add(emailLabel);

        emailField = new JTextField("");
        emailField.setBounds(150,185,280,30);
        panel.add(emailField);

        phoneLabel = new JLabel("Due Date");
        phoneLabel.setBounds(30,230,100,25);
        panel.add(phoneLabel);

        phoneField = new JTextField("");
        phoneField.setBounds(150,230,280,30);
        panel.add(phoneField);


        statusLabel = new JLabel("");
        statusLabel.setBounds(30,320,400,25);
        statusLabel.setForeground(new Color(0,128,0));
        panel.add(statusLabel);

        saveButton = new JButton("Save");
        saveButton.setBounds(120,275,100,35);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(260,275,100,35);
        clearButton.addActionListener(this);
        panel.add(clearButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==saveButton){

            if(nameField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Title is required.");
                return;
            }
            if(descriptionField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Description is required.");
                return;
            }

            if(emailField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Total marks is required.");
                return;
            }

            if(phoneField.getText().trim().isEmpty()){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Due date is required.");
                return;
            }

//            statusLabel.setForeground(new Color(0,128,0));
//            statusLabel.setText("Profile updated successfully.");

            try{
                int facultyId=106;
                ResultSet rs=connect.stmt.executeQuery("select userId from user_master where username ='"+username+"'");
                if(rs.next())
                    facultyId=rs.getInt(1);


                String query="insert into minor_project_master values(minor_project_sq.nextval,?,?,?,?,?)";
                PreparedStatement preparedStatement=connect.con.prepareStatement(query);
                preparedStatement.setString(1,nameField.getText().trim());
                preparedStatement.setString(2,descriptionField.getText().trim());
                preparedStatement.setString(3,emailField.getText().trim());
                preparedStatement.setInt(4,facultyId);
                preparedStatement.setString(5,phoneField.getText().trim());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this,"Minor Project has been added successfully","Minor Project Info",1);

                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                descriptionField.setText("");
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource()==clearButton){

            nameField.setText("");
            emailField.setText("");
            phoneField.setText("");
            descriptionField.setText("");
        }
    }
}
