package fileexplorer.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Window;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import fileexplorer.FileIO;

public class TableView extends FilePreviewView implements PropertyChangeListener {
	FileIO fileIO;

	JTable table;

	public TableView(FileIO fileIO){
		this.fileIO = fileIO;
	}

	public Container createUserInterface(Container container){
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Name"); 
		model.addColumn("Last Changed");
		model.addColumn("Typ");
		model.addColumn("Size");
		table = new JTable(model); 

		JTableHeader header = table.getTableHeader();
		header.addMouseListener(new TableHeaderMouseListener(table));

		table.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				int col = table.columnAtPoint(evt.getPoint());

				String path = (String)table.getValueAt(row,  0);
				boolean dir = table.getValueAt(row,  2).equals("Directory");
				if(dir){
					File[] data;
					data = fileIO.getFiles();

					for(int i=0; i<model.getRowCount(); i++){
						model.removeRow(i);
					}


					for(int i=0; i<data.length; i++){
						Vector vec = new Vector();
						vec.add(data[i].getName());
						vec.add(data[i].lastModified());
						vec.add(data[i].isFile() ? "File" : "Directory");
						vec.add(data[i].getTotalSpace());
						model.addRow(vec);
					}



				}else{
					
		
				}
				
				selectedFile = (String)table.getValueAt(row,  0);
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);

		container.add(scrollPane, BorderLayout.CENTER);
		container.setSize(400, 150);
		container.setVisible(true);

		File[] data;
		data = fileIO.getFiles();
		for(int i=0; i<data.length; i++){
			Vector vec = new Vector();
			vec.add(data[i].getName());
			vec.add(data[i].lastModified());
			vec.add(data[i].isFile() ? "File" : "Directory");
			vec.add(data[i].getTotalSpace());
			model.addRow(vec);
		}



		return container;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		DefaultTableModel model = new DefaultTableModel(); 
		model.addColumn("Name"); 
		model.addColumn("Last Changed");
		model.addColumn("Typ");
		model.addColumn("Size");
		
		File[] data = fileIO.getFiles();
		for(int i=0; i<data.length; i++){
			Vector vec = new Vector();
			vec.add(data[i].getName());
			vec.add(data[i].lastModified());
			vec.add(data[i].isFile() ? "File" : "Directory");
			vec.add(data[i].getTotalSpace());
			model.addRow(vec);
		}

		table.setModel(model);



	}	


}
