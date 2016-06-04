package plugins;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fileexplorer.FileIO;
import fileexplorer.gui.Window;

public class RenameFolderPlugin implements ButtonPlugin{
	Window window;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		FileIO io = window.getFileIO();
	
		String newName = JOptionPane.showInputDialog(null,"Please give the folder a new name",
                "Rename folder",
                JOptionPane.PLAIN_MESSAGE);
		
		io.renameFolder(window.getSelectedFile(), newName);
	}

	@Override
	public void setWindow(Window window) {
		this.window = window;
	}

	@Override
	public String getButtonText() {
		return "Rename Folder";
	}

}
