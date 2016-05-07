package fileexplorer.gui;

import java.awt.Container;

import javax.swing.JFrame;


public class Window {
	JFrame window;

	boolean tabs;
	boolean table;

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public Window(boolean tabs, boolean table){
		this.tabs = tabs;
		this.table = table;
	}
	public void create(){
		JFrame window = new JFrame("File Explorer");
		window.setSize(500, 500);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);		

		this.window = window;
	}

	public void createFilePreview(Container parent){
		if(table){
			TableView table = new TableView();
			table.createUserInterface(tabs ? parent : window);		
		}
	}
}
