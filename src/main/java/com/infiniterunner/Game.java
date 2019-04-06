package com.infiniterunner;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

/**
 *
 * @author Tomasz Baslyk
 */
public class Game extends JFrame {

    private GamePanel gpnl;
    private ImageIcon icon;
    private boolean spacePressed = false;
    private boolean rPressed = false;

    /**
     * Creates new form Game
     */
    public Game() {

        gpnl = new GamePanel();
        add(gpnl);
        initComponents();
        initFrame();
    }

    public void initFrame() {

        setTitle("Infinite Runner");
        setResizable(false);
        setSize(800, 600);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        icon = new javax.swing.ImageIcon(getClass().getResource("/com/infiniterunner/infoicon.png"));

        setIconImage(Toolkit.getDefaultToolkit().getImage(Game.class.getResource("/com/infiniterunner/dinoicon.png")));

        radmniHitbox.setSelected(false);
        radmniPause.setSelected(false);
        radmniMute.setSelected(false);
        radmniFPS.setSelected(false);

        pauseFix();
    }

    public void pauseFix() {
        int timerDelay = 50;

        Timer t = new Timer(timerDelay, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!gpnl.runningStatus()) {
                    radmniPause.setEnabled(false);
                } else {
                    radmniPause.setEnabled(true);
                }
            }
        });
        t.setRepeats(true);
        t.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        winLicense = new javax.swing.JDialog();
        lblIcon = new javax.swing.JLabel();
        epLink = new javax.swing.JEditorPane();
        mnbMain = new javax.swing.JMenuBar();
        mnAbout = new javax.swing.JMenu();
        mniLicense = new javax.swing.JMenuItem();
        mnOptions = new javax.swing.JMenu();
        mniRestart = new javax.swing.JMenuItem();
        radmniPause = new javax.swing.JRadioButtonMenuItem();
        radmniHitbox = new javax.swing.JRadioButtonMenuItem();
        radmniFPS = new javax.swing.JRadioButtonMenuItem();
        radmniMute = new javax.swing.JRadioButtonMenuItem();

        winLicense.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                winLicenseWindowClosing(evt);
            }
        });

        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/infiniterunner/infoicon.png"))); // NOI18N

        epLink.setBackground(new java.awt.Color(238, 238, 238));

        javax.swing.GroupLayout winLicenseLayout = new javax.swing.GroupLayout(winLicense.getContentPane());
        winLicense.getContentPane().setLayout(winLicenseLayout);
        winLicenseLayout.setHorizontalGroup(
            winLicenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(winLicenseLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(epLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );
        winLicenseLayout.setVerticalGroup(
            winLicenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(winLicenseLayout.createSequentialGroup()
                .addGroup(winLicenseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(winLicenseLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(epLink, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(winLicenseLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(lblIcon)))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        mnAbout.setText("About");

        mniLicense.setText("License");
        mniLicense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLicenseActionPerformed(evt);
            }
        });
        mnAbout.add(mniLicense);

        mnbMain.add(mnAbout);

        mnOptions.setText("Options");

        mniRestart.setText("Restart (R)");
        mniRestart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRestartActionPerformed(evt);
            }
        });
        mnOptions.add(mniRestart);

        radmniPause.setSelected(true);
        radmniPause.setText("Pause (P)");
        radmniPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radmniPauseActionPerformed(evt);
            }
        });
        mnOptions.add(radmniPause);

        radmniHitbox.setSelected(true);
        radmniHitbox.setText("Show hitbox (H)");
        radmniHitbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radmniHitboxActionPerformed(evt);
            }
        });
        mnOptions.add(radmniHitbox);

        radmniFPS.setSelected(true);
        radmniFPS.setText("Show FPS (F)");
        radmniFPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radmniFPSActionPerformed(evt);
            }
        });
        mnOptions.add(radmniFPS);

        radmniMute.setSelected(true);
        radmniMute.setText("Mute (M)");
        radmniMute.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radmniMuteActionPerformed(evt);
            }
        });
        mnOptions.add(radmniMute);

        mnbMain.add(mnOptions);

        setJMenuBar(mnbMain);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_SPACE && !gpnl.pauseStatus() && !spacePressed && !gpnl.jumpingStatus()) {
            gpnl.jumpToggle(true);
            spacePressed = true;
        }

        if (evt.getKeyCode() == KeyEvent.VK_R && !rPressed) {
            gpnl.restart();
            rPressed = true;
        }

        if (evt.getKeyCode() == KeyEvent.VK_P && gpnl.runningStatus()) {
            if (gpnl.pauseStatus()) {
                gpnl.pauseToggle(false);
                radmniPause.setSelected(false);
            } else {
                gpnl.pauseToggle(true);
                radmniPause.setSelected(true);
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_H) {
            if (gpnl.hitboxStatus()) {
                gpnl.hitboxToggle(false);
                radmniHitbox.setSelected(false);
                gpnl.repaint();
            } else {
                gpnl.hitboxToggle(true);
                radmniHitbox.setSelected(true);
                gpnl.repaint();
            }
        }

        if (evt.getKeyCode() == KeyEvent.VK_M) {
            if (gpnl.muteStatus()) {
                gpnl.muteToggle(false);
                radmniMute.setSelected(false);
            } else {
                gpnl.muteToggle(true);
                radmniMute.setSelected(true);
            }
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_F) {
            if(gpnl.fpsStatus()) {
                gpnl.fpsToggle(false);
                radmniFPS.setSelected(false);
            } else {
                gpnl.fpsToggle(true);
                radmniFPS.setSelected(true);
            }
        }
    }//GEN-LAST:event_formKeyPressed

    private void mniRestartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRestartActionPerformed
        gpnl.restart();
    }//GEN-LAST:event_mniRestartActionPerformed

    private void radmniHitboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radmniHitboxActionPerformed

        if (radmniHitbox.isSelected()) {
            gpnl.hitboxToggle(true);
            gpnl.repaint();
        } else {
            gpnl.hitboxToggle(false);
            gpnl.repaint();
        }
    }//GEN-LAST:event_radmniHitboxActionPerformed

    private void radmniPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radmniPauseActionPerformed

        if (radmniPause.isSelected()) {
            gpnl.pauseToggle(true);
        } else {
            gpnl.pauseToggle(false);
        }
    }//GEN-LAST:event_radmniPauseActionPerformed

    private void radmniMuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radmniMuteActionPerformed
        if (radmniMute.isSelected()) {
            gpnl.muteToggle(true);
        } else {
            gpnl.muteToggle(false);
        }
    }//GEN-LAST:event_radmniMuteActionPerformed

    private void mniLicenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLicenseActionPerformed

        gpnl.pauseToggle(true);

        winLicense.setAlwaysOnTop(true);
        winLicense.setModal(true);
        winLicense.setSize(320, 115);
        winLicense.setResizable(false);
        winLicense.setTitle("License");

        epLink.setContentType("text/html");
        epLink.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        epLink.setFont(new java.awt.Font("Dialog", 0, 12));
        epLink.setEditable(false);
        epLink.setText("Licensed under the MIT license (c) 2019<br>Credits to <a href='https://twitter.com/ScissorMarks/'>@ScissorsMarks</a> for the sprites <br><a href='https://github.com/tomaszbaslyk/'>My GitHub</a>");

        epLink.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent hle) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                    System.out.println(hle.getURL());
                    Desktop desktop = Desktop.getDesktop();
                    try {
                        desktop.browse(hle.getURL().toURI());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();

        final int x = (size.width - winLicense.getWidth()) / 2;
        final int y = (size.height - winLicense.getHeight() - 100) / 2;

        winLicense.setLocation(x, y);

        winLicense.setVisible(true);

    }//GEN-LAST:event_mniLicenseActionPerformed

    private void winLicenseWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_winLicenseWindowClosing
        gpnl.pauseToggle(false);
    }//GEN-LAST:event_winLicenseWindowClosing

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            spacePressed = false;
        }
        
        if(evt.getKeyCode() == KeyEvent.VK_R) {
            rPressed = false;
        }
    }//GEN-LAST:event_formKeyReleased

    private void radmniFPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radmniFPSActionPerformed
        if (radmniFPS.isSelected()) {
            gpnl.fpsToggle(true);
        } else {
            gpnl.fpsToggle(false);
        }
    }//GEN-LAST:event_radmniFPSActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane epLink;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JMenu mnAbout;
    private javax.swing.JMenu mnOptions;
    private javax.swing.JMenuBar mnbMain;
    private javax.swing.JMenuItem mniLicense;
    private javax.swing.JMenuItem mniRestart;
    private javax.swing.JRadioButtonMenuItem radmniFPS;
    private javax.swing.JRadioButtonMenuItem radmniHitbox;
    private javax.swing.JRadioButtonMenuItem radmniMute;
    private javax.swing.JRadioButtonMenuItem radmniPause;
    private javax.swing.JDialog winLicense;
    // End of variables declaration//GEN-END:variables
}
