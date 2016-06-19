package fileexplorer;

import java.beans.PropertyChangeSupport;
import java.io.File;

/**
 * TODO description
 */
public class DeleteFile {
	private final String PROPERTY_DIRECTORY = "PropertyDirectory";
	private final String PROPERTY_FILES = "PropertyFiles";

	private PropertyChangeSupport changes = new PropertyChangeSupport( this );

	private String currentDirectory;
	private File[] files;

	public String getCurrentDirectory() {
		return currentDirectory;
	}
	
	public boolean deleteFile(String selectedFile) {
		boolean result = (new File(currentDirectory+"/"+selectedFile)).delete();
		
		changes.firePropertyChange(PROPERTY_FILES, files.length, files.length-1);
		return result;
	}	
}