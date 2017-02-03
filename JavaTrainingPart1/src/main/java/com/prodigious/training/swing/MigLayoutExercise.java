package com.prodigious.training.swing;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Luis Chaves on 2/1/2017
 * to test the Mig Layout component.
 */
public class MigLayoutExercise extends JFrame {

    private JPanel jPanel = new JPanel(new MigLayout("insets 20"));
    private TitledBorder border = new TitledBorder("Personal Information");
    private JLabel nameLabel = new JLabel("Name:");
    private JTextField nameText = new JTextField(20);
    private JLabel jLabelGenre = new JLabel("Genre");

    private JRadioButton maleRadio = new JRadioButton("Male");
    private JRadioButton femaleRadio = new JRadioButton("Female");
    private ButtonGroup genreGroup = new ButtonGroup();

    private JLabel descLabel = new JLabel("Description:");
    private JTextArea descArea = new JTextArea(20,10);

    private JButton acceptButton = new JButton("Accept");
    private JButton cancelButton = new JButton("Cancel");

    private JTable jTable = new JTable(3,2);
    public MigLayoutExercise(String title){
        super(title);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        genreGroup.add(maleRadio);
        genreGroup.add(femaleRadio);
        jPanel.setBorder(border);
        jPanel.add(nameLabel);
        jPanel.add(nameText,"wrap, growx");
        jPanel.add(descLabel,"top");
        jPanel.add(new JScrollPane(descArea), "push,grow,wrap, height 30:60:");
        jPanel.add(jLabelGenre);
        jPanel.add(maleRadio, "split, align c");
        jPanel.add(femaleRadio,"wrap");
        jTable.setFillsViewportHeight(true);
        jPanel.add(new JScrollPane(jTable),"skip, wrap, grow, height 40: : 80");
        jPanel.add(acceptButton,"skip, split, align c");
        jPanel.add(cancelButton);
        add(jPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setMinimumSize(this.getPreferredSize());
    }

    public static void main(String[] args) {
        MigLayoutExercise exercise = new MigLayoutExercise("Mig Layout Exercise");
        exercise.setVisible(true);
    }

}
