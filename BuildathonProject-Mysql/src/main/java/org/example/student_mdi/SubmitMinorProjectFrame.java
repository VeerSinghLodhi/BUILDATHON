package org.example.student_mdi;


import org.example.db.ConnectWithDB;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SubmitMinorProjectFrame extends JInternalFrame implements ActionListener {

    JPanel panel;

    JLabel heading;
    JLabel avatarLabel;

    JLabel nameLabel,descriptionLabel,answerLabel;
    JLabel emailLabel;
    JLabel phoneLabel;
    JLabel roleLabel;
    JLabel statusLabel;

    JTextField nameField;
    JTextField emailField;
    JTextField phoneField;
    JTextArea descriptionField,studentAnswer;

//    JComboBox<String> roleComboBox;

    JButton saveButton;
    JButton clearButton;

    String username;
    ConnectWithDB connect;
    JComboBox minorProjects;
    JButton loadDetail;
    int projectId=101;
    public SubmitMinorProjectFrame(String username) {

        super("Submit Minor Project", true, true, true, true);
        connect=new ConnectWithDB();
        this.username=username;
        setSize(550,600);
        setLayout(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,550,600);
        panel.setBackground(Color.WHITE);
        add(panel);

        heading = new JLabel("Submit Minor Project");
        heading.setFont(new Font("Arial", Font.BOLD,22));
        heading.setBounds(20,20,300,30);
        panel.add(heading);

//        avatarLabel = new JLabel("👤");
//        avatarLabel.setFont(new Font("Segoe UI Emoji",Font.PLAIN,48));
//        avatarLabel.setBounds(210,55,60,60);
//        panel.add(avatarLabel);

        nameLabel = new JLabel("Select Minor*");
        nameLabel.setBounds(30,55,100,25);
        panel.add(nameLabel);

        minorProjects = new JComboBox();
        minorProjects.setBounds(150,55,280,30);
        panel.add(minorProjects);

        loadDetail=new JButton("Load");
        loadDetail.setBounds(435,55,80,30);
        loadDetail.addActionListener(this);
        panel.add(loadDetail);

        nameLabel = new JLabel("Project Title");
        nameLabel.setBounds(30,100,100,25);
        panel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150,100,280,30);
        panel.add(nameField);

        descriptionLabel = new JLabel("Description");
        descriptionLabel.setBounds(30,145,100,25);
        panel.add(descriptionLabel);

        descriptionField = new JTextArea("");
        descriptionField.setBounds(150,145,280,70);
        descriptionField.setBorder(new LineBorder(Color.BLACK));
        panel.add(descriptionField);

        emailLabel = new JLabel("Total Marks");
        emailLabel.setBounds(30,230,100,25);
        panel.add(emailLabel);

        emailField = new JTextField("");
        emailField.setBounds(150,230,280,30);
        panel.add(emailField);

        phoneLabel = new JLabel("Due Date");
        phoneLabel.setBounds(30,275,100,25);
        panel.add(phoneLabel);

        phoneField = new JTextField("");
        phoneField.setBounds(150,275,280,30);
        panel.add(phoneField);

        answerLabel = new JLabel("You answer");
        answerLabel.setBounds(30,320,100,25);
        panel.add(answerLabel);

        studentAnswer = new JTextArea("");
        studentAnswer.setBounds(150,320,280,70);
        studentAnswer.setBorder(new LineBorder(Color.BLACK));
        panel.add(studentAnswer);

        saveButton = new JButton("Submit");
        saveButton.setBounds(150,400,100,35);
        saveButton.addActionListener(this);
        panel.add(saveButton);

        clearButton = new JButton("Clear");
        clearButton.setBounds(300,400,100,35);
        clearButton.addActionListener(this);
        panel.add(clearButton);

        loadMinorProject();
    }

    void loadMinorProject(){
        try{
            ResultSet rs=connect.stmt.executeQuery("select * from minor_project_master");
            int i=0;
            while(rs.next()){
                minorProjects.insertItemAt(rs.getString("title"),i++);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        if(e.getSource()==saveButton){

            if(minorProjects.getSelectedIndex()==-1){
                JOptionPane.showMessageDialog(this,"select a minor project","Error",0);
                return;
            }

            if(studentAnswer.getText().trim().isEmpty()){
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Answer is required.");
                return;
            }
            try{
                int studentId=104;
                ResultSet rs=connect.stmt.executeQuery("select userId from user_master where username ='"+username+"'");
                if(rs.next())
                    studentId=rs.getInt(1);

                String query="insert into STUDENT_MINOR_PROJECT values(student_minor_sq.nextval,?,?,?,?)";

                PreparedStatement preparedStatement=connect.con.prepareStatement(query);
                preparedStatement.setInt(1,studentId);
                preparedStatement.setInt(2,projectId);
                preparedStatement.setInt(3,0);
                preparedStatement.setString(4,studentAnswer.getText().trim());
                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(this,"Minor Project has been Submitted successfully","Minor Project Info",1);
                studentAnswer.setText("");
                nameField.setText("");
                emailField.setText("");
                phoneField.setText("");
                descriptionField.setText("");
                minorProjects.setSelectedIndex(-1);

            }catch (Exception ex){
                ex.printStackTrace();
            }


        }

        if(e.getSource()==loadDetail){
            if(minorProjects.getSelectedIndex()==-1){
                JOptionPane.showMessageDialog(this,"select a minor project","Error",0);
                return;
            }
            try{
                String title=minorProjects.getSelectedItem().toString().trim();
                String query="select *from minor_project_master where title=?";
                PreparedStatement preparedStatement=connect.con.prepareStatement(query);
                preparedStatement.setString(1,title);
                ResultSet rs=preparedStatement.executeQuery();
                if(rs.next()){
                    String title_db,description,max_marks,due_date;
                    projectId=rs.getInt("PROJECTID");
                    title_db=rs.getString("title");
                    description=rs.getString("description");
                    max_marks=rs.getString("MAX_MARKS");
                    due_date=rs.getString("DUE_DATE");
                    nameField.setText(title_db);
                    nameField.setEditable(false);
                    descriptionField.setText(description);
                    descriptionField.setEditable(false);
                    emailField.setText(max_marks);
                    emailField.setEditable(false);
                    phoneField.setText(due_date);
                    phoneField.setEditable(false);
                }

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource()==clearButton){
            studentAnswer.setText("");
//            nameField.setText("");
//            emailField.setText("");
//            phoneField.setText("");
//            descriptionField.setText("");
        }
    }
}

