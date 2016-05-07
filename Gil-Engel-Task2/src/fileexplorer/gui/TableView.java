package fileexplorer.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Window;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fileexplorer.FileFinder;

public class TableView implements FilePreviewView {

	public Container createUserInterface(Container container){
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Name"); 
		model.addColumn("Last Changed");
		model.addColumn("Typ");
		model.addColumn("Size");
		JTable table = new JTable(model); 

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());

				String path = (String)table.getValueAt(row,  0);
				boolean dir = table.getValueAt(row,  2).equals("DIR");
				if(dir){
					List<String[]> data = FileFinder.getFiles(path);
					for(int i=0; i<model.getRowCount(); i++){
						model.removeRow(i);
					}
					for(int i=0; i<data.size(); i++)
						model.addRow(data.get(i));
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);

		container.add(scrollPane, BorderLayout.CENTER);
		container.setSize(400, 150);
		container.setVisible(true);

		List<String[]> data = FileFinder.getFiles(".");
		for(int i=0; i<data.size(); i++)
			model.addRow(data.get(i));
		
		return container;
	}
}
