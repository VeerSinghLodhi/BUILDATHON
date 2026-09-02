package org.example.admin_mdi;

import org.example.db.ConnectWithDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

public class ChangePasswordInternalFrame extends JInternalFrame implements ActionListener {

    JPanel panel;

    JLabel heading;
    JLabel currentLabel;
    JLabel newLabel;
    JLabel confirmLabel;
    JLabel statusLabel;

    JPasswordField currentField;
    JPasswordField newField;
    JPasswordField confirmField;

    JButton updateButton;
    JButton clearButton;

    String username;
    ConnectWithDB connect;
    public ChangePasswordInternalFrame(String username) {

        super("Change Password", true, true, true, true);
        this.username=username;
        connect=new ConnectWithDB();
        setSize(450,350);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,450,350);
        panel.setBackground(Color.WHITE);
        add(panel);

        heading = new JLabel("Change Password");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(20,20,250,30);
        panel.add(heading);

        currentLabel = new JLabel("Current Password");
        currentLabel.setBounds(20,80,130,25);
        panel.add(currentLabel);

        currentField = new JPasswordField();
        currentField.setBounds(170,80,220,30);
        panel.add(currentField);

        newLabel = new JLabel("New Password");
        newLabel.setBounds(20,130,130,25);
        panel.add(newLabel);

        newField = new JPasswordField();
        newField.setBounds(170,130,220,30);
        panel.add(newField);

        confirmLabel = new JLabel("Confirm Password");
        confirmLabel.setBounds(20,180,130,25);
        panel.add(confirmLabel);

        confirmField = new JPasswordField();
        confirmField.setBounds(170,180,220,30);
        panel.add(confirmField);

        statusLabel = new JLabel("");
        statusLabel.setBounds(20,220,370,25);
        panel.add(statusLabel);

        updateButton = new JButton("Update");
        updateButton.setBounds(90,265,120,35);
        updateButton.addActionListener(this);
        panel.add(updateButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(240,265,120,35);
        clearButton.addActionListener(this);
        panel.add(clearButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==updateButton){

            char[] current = currentField.getPassword();
            char[] newPassword = newField.getPassword();
            char[] confirmPassword = confirmField.getPassword();

            if(current.length==0 || newPassword.length==0 || confirmPassword.length==0){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("All fields are required.");
                return;

            }
            else if(!Arrays.equals(newPassword,confirmPassword)){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("New Password and Confirm Password must match.");
                return;
            }
            else if(newPassword.length<6){

                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Password must contain at least 6 characters.");
                return;
            }
            else{
                try{

                    String currentPassword=new String(current);
                    String newPasswordInString=new String(newPassword);

                    String query = "select count(*) from user_master where username=? and password=?";
                    System.out.println("username is "+username);
                    System.out.println("Password is "+currentPassword);
                    PreparedStatement preparedStatement=connect.con.prepareStatement(query);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,currentPassword);
                    ResultSet rs=preparedStatement.executeQuery();
                    int count=0;
                    if(rs.next())
                        count=rs.getInt(1);
                    if(count!=0){
                        query = "update user_master set password =? where username=?";
                        PreparedStatement preparedStatement2=connect.con.prepareStatement(query);
                        preparedStatement2.setString(1,newPasswordInString);
                        preparedStatement2.setString(2,username);
                        preparedStatement2.executeUpdate();
                        JOptionPane.showMessageDialog(this,"Password has been updated!","Password Updated",1);
                        currentField.setText("");
                        newField.setText("");
                        confirmField.setText("");
                        statusLabel.setText("");
                    }else{
                        JOptionPane.showMessageDialog(this,"Current Password is incorrect","Error",0);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }

        if(e.getSource()==clearButton){

            currentField.setText("");
            newField.setText("");
            confirmField.setText("");
            statusLabel.setText("");
        }
    }
}

