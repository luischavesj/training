package com.prodigious.training.swing.TableModelExample;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Luis Chaves on 2/2/2017.
 * to test the Table Model
 */
public class PersonViewer extends JFrame {

    private JPanel panel = new JPanel(new MigLayout());
    private TitledBorder border = new TitledBorder("Personal Information");
    private JLabel firstNameLabel = new JLabel("First Name:");
    private JTextField firstNameText = new JTextField(20);
    private JLabel lastNameLabel = new JLabel("Last Name:");
    private JTextField ageText = new JTextField(20);

    private JLabel ageLabel = new JLabel("Age:");
    private JTextField lastNameText = new JTextField(20);

    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete");

    private PersonTableModel personTableModel = new PersonTableModel();
    private JTable jTable = new JTable(personTableModel);

    public PersonViewer(){
        panel.setBorder(border);
        panel.add(firstNameLabel);
        panel.add(firstNameText, "wrap, grow");
        panel.add(lastNameLabel);
        panel.add(lastNameText,"wrap, grow");
        panel.add(ageLabel);
        panel.add(ageText, "wrap, grow");
        panel.add(addButton,"skip,split, align c");
        panel.add(deleteButton,"wrap");
        panel.add(new JScrollPane(jTable),"skip, wrap, grow, height 40: : 80");

        add(panel);
        jTable.setFillsViewportHeight(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setMinimumSize(this.getPreferredSize());

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }

    public void add() {
        if (!firstNameText.getText().isEmpty() && !lastNameText.getText().isEmpty() && !ageText.getText().isEmpty()) {
            personTableModel.addPerson(new Person(firstNameText.getText(), lastNameText.getText(), Integer.parseInt(ageText.getText()),Boolean.TRUE));
        }
    }

    public void delete() {
        if (jTable.getSelectedRow() != -1) {
            personTableModel.deletePerson(jTable.getSelectedRow());
        }
    }

    public static void main(String[] args) {
        PersonViewer viewer = new PersonViewer();
        viewer.setVisible(true);
    }
}
