package fileexplorer.gui;

import java.awt.event.ActionListener;

/**
 * TODO description
 */
public interface ButtonPlugin extends ActionListener{
	void setWindow(GUI window);
	String getButtonText();
}