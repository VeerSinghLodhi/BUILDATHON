package org.example.ui;

import org.example.admin_mdi.AdminMainFrame;
import org.example.db.ConnectWithDB;
import org.example.faculty_mdi.FacultyMainFrame;
import org.example.student_mdi.StudentMainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {

    static Color BG_COLOR = new Color(198, 234, 206); // Light Green background

    ConnectWithDB connect;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JCheckBox chkShowPassword;
    JButton btnLogin;
    JButton btnGoToSignup;

    public Login() {
        connect=new ConnectWithDB();
        setTitle("Login");
        setSize(450, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        // Apply static background color
        getContentPane().setBackground(BG_COLOR);

        // ----- Title Label -----
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(new Color(20, 100, 50));
        lblTitle.setBounds(175, 20, 120, 40);
        add(lblTitle);

        // ----- Username Label -----
        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 15));
        lblUser.setBounds(60, 90, 100, 28);
        add(lblUser);

        // ----- Username Field -----
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        txtUsername.setBounds(170, 90, 200, 28);
        add(txtUsername);

        // ----- Password Label -----
        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPass.setBounds(60, 145, 100, 28);
        add(lblPass);

        // ----- Password Field -----
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        txtPassword.setBounds(170, 145, 200, 28);
        add(txtPassword);

        // ----- Show Password Checkbox -----
        chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        chkShowPassword.setBounds(170, 178, 140, 25);
        chkShowPassword.setBackground(BG_COLOR);
        chkShowPassword.setFocusPainted(false);
        chkShowPassword.addActionListener(e -> {
            if (chkShowPassword.isSelected()) {
                txtPassword.setEchoChar((char) 0);
            } else {
                txtPassword.setEchoChar('\u2022');
            }
        });
        add(chkShowPassword);

        // ----- Login Button -----
        btnLogin = new JButton("Login");
        btnLogin.setBounds(120, 225, 100, 35);
        btnLogin.setBackground(new Color(34, 150, 70));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.addActionListener(this);
        add(btnLogin);

        // ----- Reset Button -----
        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(240, 225, 100, 35);
        btnReset.setFont(new Font("Arial", Font.PLAIN, 14));
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(e -> {
            txtUsername.setText("");
            txtPassword.setText("");
        });
        add(btnReset);

        // ----- "Don't have account?" label -----
        JLabel lblSignupPrompt = new JLabel("Don't have an account?");
        lblSignupPrompt.setFont(new Font("Arial", Font.PLAIN, 13));
        lblSignupPrompt.setBounds(100, 285, 165, 25);
        add(lblSignupPrompt);

        // ----- Sign Up Link Button -----
        btnGoToSignup = new JButton("Sign Up");
        btnGoToSignup.setBounds(270, 283, 90, 27);
        btnGoToSignup.setBackground(BG_COLOR);
        btnGoToSignup.setForeground(new Color(0, 100, 0));
        btnGoToSignup.setBorderPainted(false);
        btnGoToSignup.setFocusPainted(false);
        btnGoToSignup.setFont(new Font("Arial", Font.BOLD, 13));
        btnGoToSignup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGoToSignup.addActionListener(this);
        add(btnGoToSignup);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword()).trim();

            // Simple check — no real authentication
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {

                try{
                    String query="select username,role from user_master where username =? and password=? ";
                    PreparedStatement preparedStatement=connect.con.prepareStatement(query);
                    preparedStatement.setString(1,username);
                    preparedStatement.setString(2,password);
                    ResultSet rs=preparedStatement.executeQuery();
                    int result=0;
                    String role=null;
                    if(rs.next()) {
                        role=rs.getString("role");
                        System.out.println("Role is "+role);
                        String userName=rs.getString("username");
                        if(role.equalsIgnoreCase("ADMIN")){
                            SwingUtilities.invokeLater(() -> new AdminMainFrame(username).setVisible(true));
                        }
                        else if(role.equalsIgnoreCase("FACULTY")){
                            SwingUtilities.invokeLater(() -> new FacultyMainFrame(userName).setVisible(true));
                        }
                        else if(role.equalsIgnoreCase("STUDENT")){
                            SwingUtilities.invokeLater(() -> new StudentMainFrame(username).setVisible(true));
                        }
                        else{
                            JOptionPane.showMessageDialog(this,"Something went wrong","Error",0);
                        }
                        this.dispose();
                    }else{
                        JOptionPane.showMessageDialog(this,
                                "Invalid Username or password.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Exception Came",
                            "Exception",
                            JOptionPane.ERROR_MESSAGE);
                }
            }


        }

        if (e.getSource() == btnGoToSignup) {
            this.dispose();
            new Signup();
        }
    }
}
