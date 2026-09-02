package org.example.faculty_mdi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeThemeInternalFrame extends JInternalFrame implements ActionListener {

    private final JFrame mainFrame;

    JRadioButton metalTheme;
    JRadioButton nimbusTheme;
    JRadioButton windowsTheme;
    JButton applyButton;

    public ChangeThemeInternalFrame(JFrame mainFrame) {

        super("Change Theme", true, true, true, true);

        this.mainFrame = mainFrame;

        setSize(450,300);
        setLayout(null);

        metalTheme = new JRadioButton("Metal");
        metalTheme.setBounds(30,40,150,30);
        add(metalTheme);

        nimbusTheme = new JRadioButton("Nimbus");
        nimbusTheme.setBounds(30,80,150,30);
        add(nimbusTheme);

        windowsTheme = new JRadioButton("Windows");
        windowsTheme.setBounds(30,120,150,30);
        add(windowsTheme);

        ButtonGroup group = new ButtonGroup();
        group.add(metalTheme);
        group.add(nimbusTheme);
        group.add(windowsTheme);

        applyButton = new JButton("Apply");
        applyButton.setBounds(30,180,120,35);
        add(applyButton);

        applyButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==applyButton){

            if(metalTheme.isSelected()){
                changeTheme(UIManager.getCrossPlatformLookAndFeelClassName());
            }

            if(nimbusTheme.isSelected()){
                putNimbusTheme();
            }

            if(windowsTheme.isSelected()){
                putWindowsTheme();
            }
        }
    }

    private void putNimbusTheme(){

        try{

            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()){

                if(info.getName().equals("Nimbus")){

                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            SwingUtilities.updateComponentTreeUI(mainFrame);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void putWindowsTheme(){

        try{

            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

            SwingUtilities.updateComponentTreeUI(mainFrame);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void changeTheme(String className){

        try{

            UIManager.setLookAndFeel(className);

            SwingUtilities.updateComponentTreeUI(mainFrame);

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}

//package org.example.mdi;
//
//import javax.swing.*;
//import javax.swing.border.EmptyBorder;
//import java.awt.*;
//
///**
// * ChangeThemeInternalFrame — a standalone internal frame that lets the
// * user switch the application's Look & Feel at runtime.
// * Lives in its own class, independent of MainFrame.
// *
// * It only needs a reference to the top-level Window so it can refresh
// * the UI tree after switching themes — it doesn't touch MainFrame's
// * internals otherwise.
// */
//public class ChangeThemeInternalFrame extends JInternalFrame {
//
//    public ChangeThemeInternalFrame(Window appWindow) {
//        super("Change Theme", true, true, true, true);
//        setSize(340, 240);
//        setResizable(false);
//        setContentPane(buildContent(appWindow));
//    }
//
//    private JComponent buildContent(Window appWindow) {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
//        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
//
//        JLabel heading = new JLabel("Choose a Theme");
//        heading.setFont(heading.getFont().deriveFont(Font.BOLD, 14f));
//        heading.setAlignmentX(Component.LEFT_ALIGNMENT);
//        panel.add(heading);
//        panel.add(Box.createVerticalStrut(14));
//
//        ButtonGroup group = new ButtonGroup();
//        String currentLaf = UIManager.getLookAndFeel().getName();
//
//        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//            JRadioButton option = new JRadioButton(info.getName());
//            option.setAlignmentX(Component.LEFT_ALIGNMENT);
//            option.setSelected(info.getName().equals(currentLaf));
//            option.addActionListener(e -> applyTheme(info.getClassName(), appWindow));
//            group.add(option);
//            panel.add(option);
//            panel.add(Box.createVerticalStrut(4));
//        }
//
//        panel.add(Box.createVerticalGlue());
//        JLabel note = new JLabel("Theme applies to the whole application.");
//        note.setFont(note.getFont().deriveFont(11f));
//        note.setForeground(Color.GRAY);
//        note.setAlignmentX(Component.LEFT_ALIGNMENT);
//        panel.add(note);
//
//        return panel;
//    }
//
//    private void applyTheme(String className, Window appWindow) {
//        try {
//            UIManager.setLookAndFeel(className);
//            SwingUtilities.updateComponentTreeUI(appWindow);
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(this,
//                    "Could not apply theme: " + ex.getMessage(),
//                    "Theme Error", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//}
