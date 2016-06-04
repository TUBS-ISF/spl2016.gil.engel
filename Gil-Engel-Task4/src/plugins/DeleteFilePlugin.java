package plugins;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import fileexplorer.gui.Window;

public class DeleteFilePlugin implements ButtonPlugin{
	Window window;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean result = window.getFileIO().deleteFile(window.getSelectedFile());
		
		if(!result)
		JOptionPane.showMessageDialog(null, "Deletion of file "+window.getSelectedFile()+" went wrong. Sorry :(");
	}

	@Override
	public void setWindow(Window window) {
		this.window = window;
	}

	@Override
	public String getButtonText() {
		return "Delete File";
	}

}
