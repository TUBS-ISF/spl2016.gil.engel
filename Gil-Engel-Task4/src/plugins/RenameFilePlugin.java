package plugins;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fileexplorer.FileIO;
import fileexplorer.gui.Window;

public class RenameFilePlugin implements ButtonPlugin{
	Window window;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		FileIO io = window.getFileIO();
	
		String newName = JOptionPane.showInputDialog(null,"Please give the file a new name",
                "Rename file",
                JOptionPane.PLAIN_MESSAGE);
		
		io.renameFile(window.getSelectedFile(), newName);
	}

	@Override
	public void setWindow(Window window) {
		this.window = window;
	}

	@Override
	public String getButtonText() {
		return "Rename File";
	}

}
