package com.prodigious.training.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Luis Chaves on 1/31/2017
 * to test the swing button and window.
 */
public class ButtonExercise extends JFrame implements ActionListener, WindowListener{

    private JButton button;
    private JTextField text = new JTextField(20);
    private int numberOfClicks;

    public ButtonExercise(String title){
        super(title);
        setLocation(500,300);
        setResizable(false);
        setLayout(new FlowLayout());
        addWindowListener(this);
        button = new JButton("Click me");
        button.addActionListener(this);
        add(button);
        add(text);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        numberOfClicks++;
        text.setText("Number of Clicks " + numberOfClicks);
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        this.dispose();
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    public static void main(String[] args) {
        ButtonExercise buttonExercise = new ButtonExercise("Button Exercise");
        buttonExercise.setSize(new Dimension(350,100));
        buttonExercise.setVisible(true);
    }
}
