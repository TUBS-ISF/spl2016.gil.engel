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
	
	private void deleteRecFolder(File file){
		File[] contents = file.listFiles();
		if (contents != null) {
			for (File f : contents) {
				deleteRecFolder(f);
			}
		}
		file.delete();		
	}	
	
	public void deleteFolder(String name){
		deleteRecFolder(new File(currentDirectory+"/"+name));
		changes.firePropertyChange(PROPERTY_FILES, files.length, files.length-1);
	}
}