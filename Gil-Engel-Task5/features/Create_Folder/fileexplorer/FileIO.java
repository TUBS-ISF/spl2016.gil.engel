package fileexplorer;

import java.beans.PropertyChangeSupport;
import java.io.File;

/**
 * TODO description
 */
public class FileIO {
	private final String PROPERTY_DIRECTORY = "PropertyDirectory";
	private final String PROPERTY_FILES = "PropertyFiles";

	private PropertyChangeSupport changes = new PropertyChangeSupport( this );

	private String currentDirectory;
	private File[] files;

	public String getCurrentDirectory() {
		return currentDirectory;
	}	
	
	public boolean createFolder(String name){
		boolean result = (new File(currentDirectory+"/"+name)).mkdirs();
		if(result)
			changes.firePropertyChange(PROPERTY_FILES, files.length, files.length+1);
		
		return result;
	}
}