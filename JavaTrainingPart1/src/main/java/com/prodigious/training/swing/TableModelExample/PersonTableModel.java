package com.prodigious.training.swing.TableModelExample;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Luis Chaves on 2/2/2017
 * to test the Table Model.
 */
public final class PersonTableModel extends AbstractTableModel{

    private final List<Person> people;
    private List subscriptionList;
    private String[] columnNames = {"First Name", "Last Name", "Age", "Vegetarian"};

    public PersonTableModel(){
        people = new ArrayList<>();
        subscriptionList = new LinkedList();
    }
    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return Person.COLUMNS;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return people.get(rowIndex).getValueAt(columnIndex);
    }

    @Override
    public String getColumnName(int index){
        return columnNames[index];
    }

    public void addPerson(Person person){
        people.add(person);
        TableModelEvent event = new TableModelEvent (
                this, this.getRowCount()-1, this.getRowCount()-1,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);

        for(int i = 0; i< subscriptionList.size(); i++)
            ((TableModelListener) subscriptionList.get(i)).tableChanged(event);
    }

    public void deletePerson(int index){
        people.remove(index);
        TableModelEvent event = new TableModelEvent (
                this, this.getRowCount()-1, this.getRowCount()-1,
                TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

        for(int i = 0; i< subscriptionList.size(); i++)
            ((TableModelListener) subscriptionList.get(i)).tableChanged(event);
    }

    public void addTableModelListener (TableModelListener l) {
        subscriptionList.add (l);
    }

    public void removeTableModelListener (TableModelListener l) {
        subscriptionList.remove(l);
    }
}
