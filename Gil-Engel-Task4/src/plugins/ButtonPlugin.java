package plugins;

import java.awt.event.ActionListener;

import fileexplorer.gui.Window;

public interface ButtonPlugin extends ActionListener{
	void setWindow(Window window);
	String getButtonText();
}
