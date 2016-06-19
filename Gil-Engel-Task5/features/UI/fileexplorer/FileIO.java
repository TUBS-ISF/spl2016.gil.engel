package fileexplorer;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

	public FileIO(String directory){
		currentDirectory = directory;
	}
	public void setCurrentDirectory(String currentDirectory) {
		this.currentDirectory = currentDirectory;
	}

	public File[] getFiles(String path){
		File dir = new File(path);
		files = dir.listFiles();
		
		return files;
	}

	public File[] getFiles(){		
		return getFiles(currentDirectory);
	}
	public void changeDirectory(String directory) {
		String old = currentDirectory;

		currentDirectory = directory;
		changes.firePropertyChange(PROPERTY_DIRECTORY, old, currentDirectory);	
	}

	public void addPropertyChangeListener( PropertyChangeListener l )
	{
		changes.addPropertyChangeListener( l );
	}

	public void removePropertyChangeListener( PropertyChangeListener l )
	{
		changes.removePropertyChangeListener( l );
	}





}