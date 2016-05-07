package fileexplorer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fileexplorer.gui.TabView;
import fileexplorer.gui.TableView;
import fileexplorer.gui.Window;


public class Main {

	public static void main(String[] args) throws IOException {
		List<String> list = Arrays.asList(args);

		Window window = new Window(list.contains("tabs"), list.contains("table"));
		window.create();
		Container container = window.getWindow();

		boolean tabs = false;
		if(list.contains("tabs")){
			TabView view = new TabView(window);
			container = view.createUserInterface(window.getWindow());
			
			tabs = true;
		}

		if(list.contains("table")){
			TableView table = new TableView();
			container = table.createUserInterface(tabs ? container : window.getWindow());
		}
	}
}
