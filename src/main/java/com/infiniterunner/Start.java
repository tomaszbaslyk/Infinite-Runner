package com.infiniterunner;

import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import java.util.Properties;
import javax.swing.UIManager;

/**
 * @author Tomasz Baslyk
 */
public class Start {

    public static void main(String args[]) {
        try {
            Properties props = new Properties();

            // Menu logo
            props.put("logoString", "...");

            props.put("centerWindowTitle", "off");
            
            props.put("focusColor", "51 51 51");

            AcrylLookAndFeel.setCurrentTheme(props);

            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameManager().setVisible(true);
            }
        });
    }
}
