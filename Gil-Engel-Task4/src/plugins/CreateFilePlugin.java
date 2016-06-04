package plugins;

import java.awt.event.ActionEvent;

import fileexplorer.gui.Window;

public class CreateFilePlugin implements ButtonPlugin{
	Window window;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		window.getFileIO().createFile("New File");
	}

	@Override
	public void setWindow(Window window) {
		this.window = window;
	}

	@Override
	public String getButtonText() {
		return "Create New (Text)File";
	}

}
