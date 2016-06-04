package plugins;

import java.awt.event.ActionEvent;

import fileexplorer.gui.Window;

public class CreateFolderPlugin implements ButtonPlugin{
	Window window;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		window.getFileIO().createFolder("New Folder");
	}

	@Override
	public void setWindow(Window window) {
		this.window = window;
	}

	@Override
	public String getButtonText() {
		return "Create New Folder";
	}

}
