package com.infiniterunner;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 * @author Tomasz Baslyk
 */
public class GamePanel extends JPanel implements ActionListener {

    private Player player;
    private Entity enemy, cloud;
    private boolean animationSwap, jumpInitiated, hitboxEnabled, pauseEnabled, muteEnabled, running, fpsCounterEnabled;
    private int countScore, tutorialPopupTime, loopCounter, jumpDifficulty;
    private double fpsCounter, fps;
    private JLabel lblCounter;
    private JButton btnEasy, btnHard;

    private Timer mainTimer;
    private Font pixelFont;

    public GamePanel() {
        animationSwap = false;
        running = false;
        hitboxEnabled = false;
        fpsCounterEnabled = false;
        pauseEnabled = false;
        muteEnabled = false;
        jumpInitiated = false;
        jumpDifficulty = 0;
        countScore = 0;
        loopCounter = 0;
        tutorialPopupTime = 0;
        fpsCounter = 0;
        fps = 0;

        FxPlayer startSound = new FxPlayer("/com/infiniterunner/beginsound.wav");

        // instantiates all entities
        enemy = new Entity(3000, 300, "dino11", "dino12");
        player = new Player();
        createCloud();

        // initiaties all swing components and game timer
        initFont();
        initPanel();
        initButtons();
        initTimer();
    }

    // JPanel attributes
    public final void initPanel() {
        setSize(800, 600);
        setLayout(null);

        lblCounter = new JLabel("0");
        lblCounter.setFont(pixelFont);
        lblCounter.setForeground(new java.awt.Color(255, 255, 255));
        lblCounter.setBounds(300, 50, 200, 80);
        lblCounter.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblCounter, BorderLayout.PAGE_END);
        lblCounter.setVisible(false);
    }

    // Difficulty buttons
    public void initButtons() {
        btnEasy = new JButton("EASY");
        btnEasy.setFont(pixelFont);
        btnEasy.setForeground(new java.awt.Color(255, 255, 255));
        btnEasy.setBounds(150, 350, 200, 80);
        btnEasy.setBorder(null);
        btnEasy.setBorderPainted(false);
        btnEasy.setContentAreaFilled(false);
        btnEasy.setFocusPainted(false);
        btnEasy.setVisible(true);
        add(btnEasy);
        btnEasy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startEasy();
            }
        });
        btnHard = new JButton("HARD");
        btnHard.setFont(pixelFont);
        btnHard.setForeground(new java.awt.Color(255, 255, 255));
        btnHard.setBounds(440, 350, 200, 80);
        btnHard.setBorder(null);
        btnHard.setBorderPainted(false);
        btnHard.setContentAreaFilled(false);
        btnHard.setFocusPainted(false);
        btnHard.setVisible(true);
        add(btnHard);
        btnHard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startHard();
            }
        });
    }

    // Game timer
    public final void initTimer() {
        mainTimer = new Timer(33, this);
        mainTimer.setRepeats(true);
        mainTimer.start();
    }

    // Timer loop
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running && !pauseEnabled) {

            animatePlayer(loopCounter, 4);
            moveEnemy();
            moveCloud(loopCounter, 20);
            checkCollision();
            scoreCounter();

            loopCounter++;
            fps = calculateFramesPerSecond();
            //System.out.println(jumpDifficulty);
        }
        // calls paintComponent method
        repaint();
    }

    // 8-bit font
    public final void initFont() {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            pixelFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/com/infiniterunner/PressStart2P.ttf")).deriveFont(28f);
            ge.registerFont(pixelFont);

            /*
             String[] allFonts = ge.getAvailableFontFamilyNames();
            
             for(String font : allFonts) {
             System.out.println(font);
             }
             */
        } catch (FontFormatException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load custom font!\n" + ex.getMessage());
        }
    }

    // game score counter
    public void scoreCounter() {
        countScore++;
        String stringCount = String.valueOf(countScore);
        lblCounter.setText(stringCount);
    }

    // fps calculator
    public int calculateFramesPerSecond() {
        int fps = (int) (1000 / (fpsCounter - (fpsCounter = System.currentTimeMillis())));
        fps *= -1;
        return fps;
    }

    public void startEasy() {
        jumpDifficulty = 118;
        restart();
        running = true;
        remove(btnEasy);
        remove(btnHard);
        lblCounter.setVisible(true);
    }

    public void startHard() {
        jumpDifficulty = 149;
        restart();
        running = true;
        remove(btnEasy);
        remove(btnHard);
        lblCounter.setVisible(true);
    }

    /* paint component method
     * draws all entities etc. depending on the game state (running)
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (running) {
            drawBackground(g);
            drawInstructions(g);
            drawCloud(g);
            drawPlayer(g);
            drawEnemy(g);
            drawCollision(g);
            drawJump();
            drawFramesPerSecondCounter(g);
        } else {
            drawBackground(g);
            drawCloud(g);
            drawPlayer(g);
            drawEnemy(g);
            drawCollision(g);
            drawFramesPerSecondCounter(g);
            drawMenuScreen(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    // method for animating sprites
    public void animatePlayer(int animateTime, int animateSpeed) {
        double result = animateTime % animateSpeed;

        if (result == 0) {
            if (!animationSwap) {
                animationSwap = true;
            } else {
                animationSwap = false;
            }
        }
    }

    // collision checker
    public void checkCollision() {
        Rectangle playerBounds = player.getBounds();
        Rectangle enemyBounds = enemy.getBounds();

        if (enemyBounds.intersects(playerBounds)) {
            if (running) {
                lost();
            }
        }
    }

    public void createCloud() {
        cloud = new Entity(800, 50, "cloud" + Randomizer.randomizeNumber(4));
    }

    public void createEnemy() {
        int randomizedDino = Randomizer.randomizeNumber(3);

        String firstDino = "dino" + randomizedDino + "1";
        String secondDino = "dino" + randomizedDino + "2";

        int randomizedX = (Randomizer.randomizeNumber(4) * 100) + 800;

        enemy = new Entity(randomizedX, 300, firstDino, secondDino);
    }

    public void drawBackground(Graphics g) {
        ImageIcon i = new javax.swing.ImageIcon(getClass().getResource("/com/infiniterunner/background.png"));
        Image background = i.getImage();

        g.drawImage(background, 0, 0, this);

    }

    public void drawCloud(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(cloud.getImage1(), cloud.getX(), cloud.getY(), this);
    }

    public void drawCollision(Graphics g) {
        if (hitboxEnabled) {
            Rectangle playerBounds = player.getBounds();
            Rectangle enemyBounds = enemy.getBounds();

            int xP = (int) playerBounds.getX();
            int yP = (int) playerBounds.getY();
            int wP = (int) playerBounds.getWidth();
            int hP = (int) playerBounds.getHeight();

            int xO = (int) enemyBounds.getX();
            int yO = (int) enemyBounds.getY();
            int wO = (int) enemyBounds.getWidth();
            int hO = (int) enemyBounds.getHeight();

            g.drawRect(xP, yP, wP, hP);
            g.drawRect(xO, yO, wO, hO);
        }
    }

    public void drawEnemy(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (!animationSwap) {
            g2.drawImage(enemy.getImage1(), enemy.getX(), enemy.getY(), this);
        } else {
            g2.drawImage(enemy.getImage2(), enemy.getX(), enemy.getY(), this);
        }
    }

    public void drawFramesPerSecondCounter(Graphics g) {
        if (fpsCounterEnabled) {
            String fpsString = String.valueOf(fps);
            g.drawString(fpsString + " fps", 10, 20);
        }
    }

    public void drawInstructions(Graphics g) {
        if (tutorialPopupTime < 150) {
            ImageIcon i = new javax.swing.ImageIcon(getClass().getResource("/com/infiniterunner/instructions.png"));
            Image instructions = i.getImage();

            g.drawImage(instructions, 0, 0, this);
            tutorialPopupTime++;
        }
    }

    public void drawJump() {
        if (jumpInitiated) {
            boolean jumpComplete = player.jump(jumpDifficulty);

            if (jumpComplete) {
                jumpInitiated = false;
            }
        }
    }

    public void drawMenuScreen(Graphics g) {
        ImageIcon i = new javax.swing.ImageIcon(getClass().getResource("/com/infiniterunner/menuScreen.png"));
        Image menuScreen = i.getImage();

        g.drawImage(menuScreen, 0, 0, this);
    }

    public void drawPlayer(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        if (player.getVisible()) {
            if (!animationSwap) {
                g2.drawImage(player.getImage1(), player.getX(), player.getY(), this);
            } else {
                g2.drawImage(player.getImage2(), player.getX(), player.getY(), this);
            }
        }
    }

    public void moveCloud(int cloudTime, int moveSpeed) {
        double result = cloudTime % moveSpeed;

        if (result == 0) {

            cloud.moveHorizontal(-20);

            if (cloud.getX() <= -800) {
                createCloud();
                cloud.setX(800);
            }
        }
    }

    public void moveEnemy() {
        enemy.moveHorizontal(-15);

        if (enemy.getX() <= -110) {
            createEnemy();
        }
    }

    public void lost() {
        tutorialPopupTime = 150;
        player.setVisible(false);
        backToDifficultyScreen();

        if (!muteEnabled) {
            FxPlayer deathSound = new FxPlayer("/com/infiniterunner/deathsound.wav");
            deathSound.play();
        }
    }

    public void restart() {
        pauseEnabled = false;
        jumpInitiated = false;
        player.setJumpMaxReached(false);
        player.setVisible(true);
        player.setY(300);
        enemy.setX(2000);
        cloud.setX(500);
        countScore = 0;

        running = true;
        createCloud();

        if (!muteEnabled) {
            FxPlayer restartSound = new FxPlayer("/com/infiniterunner/beginsound.wav");
            restartSound.play();
        }

    }

    public void backToDifficultyScreen() {
        running = false;
        initButtons();
    }

    // Setters
    public void hitboxToggle(boolean hitboxEnabled) {
        this.hitboxEnabled = hitboxEnabled;
    }

    public void pauseToggle(boolean pauseEnabled) {
        this.pauseEnabled = pauseEnabled;
    }

    public void muteToggle(boolean muteEnabled) {
        this.muteEnabled = muteEnabled;
    }

    public void jumpToggle(boolean jumpEnabled) {
        jumpInitiated = jumpEnabled;
    }

    public void fpsToggle(boolean fpsCounterEnabled) {
        this.fpsCounterEnabled = fpsCounterEnabled;
    }

    // Getters
    public boolean pauseStatus() {
        return pauseEnabled;
    }

    public boolean hitboxStatus() {
        return hitboxEnabled;
    }

    public boolean runningStatus() {
        return running;
    }

    public boolean muteStatus() {
        return muteEnabled;
    }

    public boolean jumpingStatus() {
        return jumpInitiated;
    }

    public boolean playerAboveGround() {
        int y = player.getY();

        if (y < 300) {
            return true;
        } else {
            return false;
        }
    }

    public boolean fpsStatus() {
        return fpsCounterEnabled;
    }

}
