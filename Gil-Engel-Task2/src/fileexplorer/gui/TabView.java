package fileexplorer.gui;

import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import fileexplorer.FileExplorer;

public class TabView implements  FilePreviewView, FileExplorer{
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
					tabPane.addTab("+", panel);		    
					
					parent.createFilePreview((Container)tabPane.getSelectedComponent());
				}
			}

		});

		JPanel panel = new JPanel();
		tabPane.addTab("Tab 1", panel);

		JPanel panel2 = new JPanel();
		tabPane.addTab("+", panel2);
		
		
		
		
		container.add(tabPane);

		return panel;
	}		

}
