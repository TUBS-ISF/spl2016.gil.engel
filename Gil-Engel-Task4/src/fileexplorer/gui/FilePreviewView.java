package fileexplorer.gui;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JSplitPane;

import fileexplorer.FileIO;

public abstract class FilePreviewView {
	Component createUserInterface(Container splitPlane) {
		return null;
	}
	
	protected FileIO fileIO = null;
	
	public String getSelectedFile() {
		return selectedFile;
	}

	public void setSelectedFile(String selectedFile) {
		this.selectedFile = selectedFile;
	}

	protected String selectedFile;
}
