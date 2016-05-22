package fileexplorer.gui;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import fileexplorer.FileIO;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.AbstractListModel;
import javax.swing.BoxLayout;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Window {
	FilePreviewView currentFileView;
	
	FileIO fileIO;
	
	JFrame window;

	boolean tabs;
	boolean table;
	private JSplitPane splitPane; 
	private JPanel panel;

	private DefaultListModel<String> partitionModel;
	private JPanel panel_1;
	private JLabel lblDrives;
	
	private JToolBar toolBar;
	
	// #ifdef Partitions_Harddrive
		private JList<String> partitionsList;
		private JButton btnNewButton;
		private JButton btnDeleteDirectory;
		private JButton btnRenameDirectory;
	// #endif
	
	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}

	public Window(boolean tabs, boolean table){
		this.tabs = tabs;
		this.table = table;
		
		partitionModel = new DefaultListModel<String>();
		fileIO = new FileIO(System.getProperty("user.dir"));
		fileIO.getFiles();
	}
	/**
	 * @wbp.parser.entryPoint
	 */
	public void create(){
		JFrame window = new JFrame("File Explorer");
		window.setSize(800, 500);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);		

		
		
		this.window = window;
		
		
		
		splitPane = new JSplitPane();
		window.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		panel = new JPanel();
		splitPane.setRightComponent(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		// #ifdef Partitions_Harddrive
			partitionsList = new JList<String>();
			GridBagConstraints gbc_list_1 = new GridBagConstraints();
			gbc_list_1.insets = new Insets(0, 0, 5, 0);
			gbc_list_1.fill = GridBagConstraints.BOTH;
			gbc_list_1.gridx = 0;
			gbc_list_1.gridy = 1;
			panel_1.add(partitionsList, gbc_list_1);
			
			partitionsList.addListSelectionListener(new ListSelectionListener(){

				@Override
				public void valueChanged(ListSelectionEvent e) {
					 fileIO.changeDirectory(partitionModel.get(e.getLastIndex()));
				}
				
			});
			
			
			
			lblDrives = new JLabel("Drives");
			GridBagConstraints gbc_lblDrives = new GridBagConstraints();
			gbc_lblDrives.insets = new Insets(0, 0, 5, 0);
			gbc_lblDrives.gridx = 0;
			gbc_lblDrives.gridy = 2;
			panel_1.add(lblDrives, gbc_lblDrives);
		// #endif
		
			toolBar = new JToolBar();
			window.getContentPane().add(toolBar, BorderLayout.NORTH);

		// #ifdef Create_Folder
			btnNewButton = new JButton("Create Directory");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					fileIO.createFolder("New Folder");
				}
			});
			toolBar.add(btnNewButton);
		// #endif
			
		// #ifdef Delete_Folder			
			btnDeleteDirectory = new JButton("Delete Directory");
			btnDeleteDirectory.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					fileIO.deleteFolder(currentFileView.selectedFile);
				}
			});
			toolBar.add(btnDeleteDirectory);
		// #endif
			
		// #ifdef Rename_Folder
//@			
//@			btnRenameDirectory = new JButton("Rename Directory");
//@			btnRenameDirectory.addActionListener(new ActionListener() {
//@				public void actionPerformed(ActionEvent arg0) {
//@					
//@					String newName = JOptionPane.showInputDialog(null,"Please give the folder a new name",
//@                            "Rename folder",
//@                            JOptionPane.PLAIN_MESSAGE);
//@					
//@					fileIO.renameFolder(currentFileView.getSelectedFile(), newName);
//@					
//@				}
//@			});
//@			toolBar.add(btnRenameDirectory);			
		// #endif
			
		createSideBar(window);
		
	}

	public void createSideBar(Container parent){
		// #ifdef Partitions_Harddrive
			ArrayList<String> p = (ArrayList<String>) fileIO.getPartitions();
			for(String partition : p){
				partitionModel.addElement(partition);
				
			}
			
			partitionsList.setModel(partitionModel);
		// #endif
	}


	public void createFilePreview(Container parent){
		if(table){
			TableView table = new TableView(fileIO);
			table.createUserInterface(parent);	

			if(currentFileView instanceof TableView){
				fileIO.removePropertyChangeListener(((TableView)currentFileView));
			}
			
			currentFileView = table;
			
			fileIO.addPropertyChangeListener(table);
		}
	}
	
	public void createTabView(){
		TabView view = new TabView(this);
		view.createUserInterface(panel);
		
		tabs = true;
	}
	
	public void createTableView(){
		
	}
}
