package org.example.admin_mdi;

import org.example.db.ConnectWithDB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


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

    JTable studentTable;

//    private String username,email,password;
//    private JTable table;
//    private JButton button;
//    public LoggedIn(String username,String email,String password){
//        this.username=username;
//        this.email=email;
//        this.password=password;
//        setLayout(null);
//
//        DefaultTableModel model=new DefaultTableModel();
//        model.addColumn("Srno");
//        model.addColumn("Username");
//        model.addColumn("Email");
//        model.addColumn("Password");
//
//        table=new JTable(model);
////        table.setEnabled(false);
//        table.setBackground(Color.orange);
//
//        for(int i=1;i<=100;i++)
//            model.addRow(new Object[]{i,username,email,password});
//
//
//        JScrollPane scrollPane=new JScrollPane(table);
//        scrollPane.setBounds(100,100,200,100);
//        add(scrollPane);

DefaultTableModel model;
ConnectWithDB connect=new ConnectWithDB();
    public ProfileInternalFrame() {


        super("All Student", true, true, true, true);
    connect=new ConnectWithDB();
        setSize(500,450);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,500,450);
        panel.setBackground(Color.WHITE);
        add(panel);

        heading = new JLabel("All Student");
        heading.setFont(new Font("Arial", Font.BOLD,22));
        heading.setBounds(20,20,200,30);
        panel.add(heading);

//        avatarLabel = new JLabel("👤");
//        avatarLabel.setFont(new Font("Segoe UI Emoji",Font.PLAIN,48));
//        avatarLabel.setBounds(210,55,60,60);
//        panel.add(avatarLabel);

        model=new DefaultTableModel();
        model.addColumn("Enrollment");
        model.addColumn("Full Name");
        model.addColumn("Semester");
        model.addColumn("Branch");


        loadStudents();
        studentTable=new JTable(model);

        JScrollPane scrollPane=new JScrollPane(studentTable);
        scrollPane.setBounds(30,70,450,250);
        panel.add(scrollPane);



        saveButton = new JButton("Show");
        saveButton.setBounds(120,360,100,35);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(260,360,100,35);
        clearButton.addActionListener(this);
        panel.add(clearButton);


    }

    void loadStudents(){
        try{
            ResultSet rs=connect.stmt.executeQuery("select * from student_master ");
            while (rs.next()) {
                System.out.println("Muskan");
                model.addRow(new Object[]{rs.getString("ENROLLMENT"),rs.getString("NAME"),rs.getString("BRANCH"),rs.getString("SEM")});
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==saveButton){

        }

        if(e.getSource()==clearButton){

        }
    }
}
