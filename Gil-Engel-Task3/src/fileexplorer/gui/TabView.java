package fileexplorer.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fileexplorer.FileExplorer;

public class TabView extends FilePreviewView implements  FileExplorer{
	Window parent;
	
	public TabView(Window parent){
		this.parent = parent;
	}
	
	public Container createUserInterface(Container container){

		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(tabPane.getSelectedIndex() == tabPane.getTabCount()-1 && tabPane.getSelectedIndex() > 0){
					tabPane.setTitleAt(tabPane.getSelectedIndex(), "Tab "+tabPane.getTabCount());
					Object o = tabPane.getTabComponentAt(tabPane.getSelectedIndex());
					JPanel panel = new JPanel();
					panel.setLayout(new GridLayout(1, 0, 0, 0));
					tabPane.addTab("+", panel);		    
					
					parent.createFilePreview((Container)tabPane.getSelectedComponent());
				}
			}

		});

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		tabPane.addTab("Tab 1", panel);
		
		parent.createFilePreview(panel);
		

		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 0, 0, 0));
		tabPane.addTab("+", panel2);
		
		

		
		container.add(tabPane,  BorderLayout.CENTER);

		return panel;
	}		

}
