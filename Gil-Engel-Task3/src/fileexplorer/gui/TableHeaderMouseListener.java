package fileexplorer.gui;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JTable;

public class TableHeaderMouseListener extends MouseAdapter {
    
    private JTable table;
     
    public TableHeaderMouseListener(JTable table) {
        this.table = table;
    }
     
    public void mouseClicked(MouseEvent event) {
        Point point = event.getPoint();
        int column = table.columnAtPoint(point);
         
    }
}