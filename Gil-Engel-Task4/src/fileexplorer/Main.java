package fileexplorer;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import fileexplorer.gui.TabView;
import fileexplorer.gui.TableView;
import fileexplorer.gui.Window;
import plugins.ButtonPlugin;
import plugins.CreateFilePlugin;
import plugins.CreateFolderPlugin;
import plugins.DeleteFilePlugin;
import plugins.DeleteFolderPlugin;
import plugins.RenameFilePlugin;
import plugins.RenameFolderPlugin;

public class Main {

	public static void main(String[] args) throws IOException {
		List<String> list = Arrays.asList(args);

		ArrayList<ButtonPlugin> plugins = new ArrayList<>();
		
		// #ifdef Create_Folder
			plugins.add(new CreateFolderPlugin());
		// #endif
			
		// #ifdef Delete_Folder			
			plugins.add(new DeleteFolderPlugin());
		// #endif
			
		// #ifdef Rename_Folder
			plugins.add(new RenameFolderPlugin());
		// #endif
			
		// #ifdef Create_File
			plugins.add(new CreateFilePlugin());			
		// #endif
		
		// #ifdef Delete_File
			plugins.add(new DeleteFilePlugin());
		// #endif
			
		// #ifdef Rename_File
			plugins.add(new RenameFilePlugin());		
		// #endif
			
		Window window = new Window(plugins, list.contains("tabs"), list.contains("table"));
		window.create();
		Container container = window.getWindow();

		boolean tabs = false;
		if(list.contains("tabs")){		
			window.createTabView();
			tabs = true;
		}
	}
}
