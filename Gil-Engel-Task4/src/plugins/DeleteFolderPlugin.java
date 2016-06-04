package plugins;

import java.awt.event.ActionEvent;

import fileexplorer.gui.Window;

public class DeleteFolderPlugin implements ButtonPlugin{
	Window window;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		window.getFileIO().deleteFolder(window.getSelectedFile());
	}

	@Override
	public void setWindow(Window window) {
		this.window = window;
	}

	@Override
	public String getButtonText() {
		return "Delete Folder";
	}

}
