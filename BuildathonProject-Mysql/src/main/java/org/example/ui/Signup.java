package org.example.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame implements ActionListener {

    // =====================================================================
    // TODO: Replace BG_COLOR with a static background image using ImageIcon
    //       and render it inside paintComponent() of a custom JPanel.
    // =====================================================================
    static Color BG_COLOR = new Color(198, 234, 206); // Light green background

    JTextField txtFullName;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JPasswordField txtConfirmPassword;
    JCheckBox chkShowPassword;
    JButton btnSignup;
    JButton btnGoToLogin;

    public Signup() {
        setTitle("Sign Up");
        setSize(470, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);

        // Apply static background color
        getContentPane().setBackground(BG_COLOR);

        // ----- Title Label -----
        JLabel lblTitle = new JLabel("Sign Up");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 26));
        lblTitle.setForeground(new Color(20, 100, 50));
        lblTitle.setBounds(175, 20, 130, 40);
        add(lblTitle);

        // ----- Full Name Label -----
        JLabel lblFullName = new JLabel("Full Name:");
        lblFullName.setFont(new Font("Arial", Font.PLAIN, 15));
        lblFullName.setBounds(50, 85, 110, 28);
        add(lblFullName);

        // ----- Full Name Field -----
        txtFullName = new JTextField();
        txtFullName.setFont(new Font("Arial", Font.PLAIN, 15));
        txtFullName.setBounds(180, 85, 210, 28);
        add(txtFullName);

        // ----- Username Label -----
        JLabel lblUser = new JLabel("Username:");
        lblUser.setFont(new Font("Arial", Font.PLAIN, 15));
        lblUser.setBounds(50, 135, 110, 28);
        add(lblUser);

        // ----- Username Field -----
        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 15));
        txtUsername.setBounds(180, 135, 210, 28);
        add(txtUsername);

        // ----- Password Label -----
        JLabel lblPass = new JLabel("Password:");
        lblPass.setFont(new Font("Arial", Font.PLAIN, 15));
        lblPass.setBounds(50, 185, 110, 28);
        add(lblPass);

        // ----- Password Field -----
        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        txtPassword.setBounds(180, 185, 210, 28);
        add(txtPassword);

        // ----- Confirm Password Label -----
        JLabel lblConfirm = new JLabel("Confirm Pass:");
        lblConfirm.setFont(new Font("Arial", Font.PLAIN, 15));
        lblConfirm.setBounds(50, 235, 120, 28);
        add(lblConfirm);

        // ----- Confirm Password Field -----
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setFont(new Font("Arial", Font.PLAIN, 15));
        txtConfirmPassword.setBounds(180, 235, 210, 28);
        add(txtConfirmPassword);

        // ----- Show Password Checkbox -----
        chkShowPassword = new JCheckBox("Show Password");
        chkShowPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        chkShowPassword.setBounds(180, 268, 140, 25);
        chkShowPassword.setBackground(BG_COLOR);
        chkShowPassword.setFocusPainted(false);
        chkShowPassword.addActionListener(e -> {
            char echo = chkShowPassword.isSelected() ? (char) 0 : '\u2022';
            txtPassword.setEchoChar(echo);
            txtConfirmPassword.setEchoChar(echo);
        });
        add(chkShowPassword);

        // ----- Sign Up Button -----
        btnSignup = new JButton("Sign Up");
        btnSignup.setBounds(120, 315, 110, 35);
        btnSignup.setBackground(new Color(34, 150, 70));
        btnSignup.setForeground(Color.WHITE);
        btnSignup.setFocusPainted(false);
        btnSignup.setFont(new Font("Arial", Font.BOLD, 14));
        btnSignup.addActionListener(this);
        add(btnSignup);

        // ----- Reset Button -----
        JButton btnReset = new JButton("Reset");
        btnReset.setBounds(250, 315, 100, 35);
        btnReset.setFont(new Font("Arial", Font.PLAIN, 14));
        btnReset.setFocusPainted(false);
        btnReset.addActionListener(e -> {
            txtFullName.setText("");
            txtUsername.setText("");
            txtPassword.setText("");
            txtConfirmPassword.setText("");
        });
        add(btnReset);

        // ----- "Already have account?" label -----
        JLabel lblLoginPrompt = new JLabel("Already have an account?");
        lblLoginPrompt.setFont(new Font("Arial", Font.PLAIN, 13));
        lblLoginPrompt.setBounds(80, 375, 185, 25);
        add(lblLoginPrompt);

        // ----- Login Link Button -----
        btnGoToLogin = new JButton("Login");
        btnGoToLogin.setBounds(268, 373, 75, 27);
        btnGoToLogin.setBackground(BG_COLOR);
        btnGoToLogin.setForeground(new Color(0, 100, 0));
        btnGoToLogin.setBorderPainted(false);
        btnGoToLogin.setFocusPainted(false);
        btnGoToLogin.setFont(new Font("Arial", Font.BOLD, 13));
        btnGoToLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnGoToLogin.addActionListener(this);
        add(btnGoToLogin);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnSignup) {
            String fullName    = txtFullName.getText().trim();
            String username    = txtUsername.getText().trim();
            String password    = new String(txtPassword.getPassword()).trim();
            String confirmPass = new String(txtConfirmPassword.getPassword()).trim();

            // Basic validation — no real signup logic
            if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill in all fields.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!password.equals(confirmPass)) {
                JOptionPane.showMessageDialog(this,
                        "Passwords do not match!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Account created for: " + fullName + "\nYou can now log in.",
                    "Signup Successful",
                    JOptionPane.INFORMATION_MESSAGE);

            // Go back to Login after successful signup
            this.dispose();
            new Login();
        }

        if (e.getSource() == btnGoToLogin) {
            this.dispose();
            new Login();
        }
    }
}
