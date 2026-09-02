package org.example.faculty_mdi;

import org.example.ui.Login;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.HashMap;
import java.util.Map;

public class FacultyMainFrame extends JFrame implements ActionListener {

    private JDesktopPane desktopPane;

    private JButton dashboardBtn;
    private JButton profileBtn;
    private JButton changePasswordBtn;
    private JButton changeThemeBtn;
    private JButton settingsBtn;
    private JButton exitBtn;
    private JButton labMarkBtn;
    private JButton externalMarkBtn;
    private JButton minorProjectMarkBtn;
    private JButton midTermBtn;

    private final Map<String, JInternalFrame> openFrames = new HashMap<>();

    String username;
    public FacultyMainFrame(String username) {

        setTitle("Faculty Management System");
        this.username=username;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        setSize(1050,650);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(198, 234, 206));

        add(buildSidebar(),BorderLayout.WEST);
        add(desktopPane,BorderLayout.CENTER);

//        openFrame("Dashboard");
    }

    private JPanel buildSidebar(){

        JPanel sidebar=new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar,BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(190,0));
        sidebar.setBorder(new EmptyBorder(10,10,10,10));

        dashboardBtn=createButton("Dashboard");
        profileBtn=createButton("Assignment");
        changePasswordBtn=createButton("Minor Project");
        changeThemeBtn=createButton("Change Theme");
        settingsBtn=createButton("Change Password");
        exitBtn=createButton("Logout");
        labMarkBtn=createButton("Lab Mark");
        externalMarkBtn=createButton("External Mark");
        minorProjectMarkBtn=createButton("Minor Project Mark");
        midTermBtn=createButton("Mid Term");


        sidebar.add(dashboardBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(profileBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(changePasswordBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(changeThemeBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(settingsBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(labMarkBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(externalMarkBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(minorProjectMarkBtn);
        sidebar.add(Box.createVerticalStrut(10));

        sidebar.add(midTermBtn);
        sidebar.add(Box.createVerticalStrut(10));




        sidebar.add(Box.createVerticalGlue());

        sidebar.add(exitBtn);

        return sidebar;
    }

    private JButton createButton(String text){

        JButton button=new JButton(text);

        button.setMaximumSize(new Dimension(Integer.MAX_VALUE,40));
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFocusPainted(false);

        button.addActionListener(this);

        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==dashboardBtn){
            openFrame("Dashboard");
        }

        if(e.getSource()==profileBtn){
            openFrame("Profile");
        }

        if(e.getSource()==changePasswordBtn){
            openFrame("Change Password");
        }

        if(e.getSource()==changeThemeBtn){
            openFrame("Change Theme");
        }

        if(e.getSource()==settingsBtn){
            openFrame("Settings");
//            openFrame("Settings");
        }

        if(e.getSource()==exitBtn){
            int ans=JOptionPane.showConfirmDialog(this,"Are you sure want to logout?","Confirmation",2);
//            System.out.println("Answer is "+ans);
            if(ans==0) {
                this.dispose();
                new Login();
            }
        }
    }

    private void openFrame(String item){

        JInternalFrame existing=openFrames.get(item);

        if(existing!=null && !existing.isClosed()){

            try{
                existing.setSelected(true);
                existing.toFront();
            }catch(PropertyVetoException ex){
                ex.printStackTrace();
            }

            return;
        }

        JInternalFrame frame=null;

        switch(item){

            case "Dashboard":
                frame=new DashboardInternalFrame();
                break;

            case "Profile":
                frame=new ProfileInternalFrame("");
                break;

            case "Change Password":
                frame=new AddMinorProjectFrame(username);
                break;

            case "Change Theme":
                frame=new ChangeThemeInternalFrame(this);
                break;

            case "Settings":
                frame=new ChangePasswordInternalFrame(username);
                break;
        }

        if(frame==null)
            return;

        frame.setLocation(30+openFrames.size()*25,
                30+openFrames.size()*25);

        openFrames.put(item,frame);

        desktopPane.add(frame);

        frame.setVisible(true);

        try{
            frame.setSelected(true);
        }catch(PropertyVetoException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(() -> new FacultyMainFrame("").setVisible(true));
    }
}
