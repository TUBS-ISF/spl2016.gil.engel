package fileexplorer;
import java.util.List;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class FileIO{
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

	public boolean createFolder(String name){
		boolean result = (new File(currentDirectory+"/"+name)).mkdirs();
		if(result)
			changes.firePropertyChange(PROPERTY_FILES, files.length, files.length+1);
		
		return result;
	}
	public boolean createFile(String name) {
		boolean result;
		try {
			result = (new File(currentDirectory+"/"+name)).createNewFile();
			if(result)
				changes.firePropertyChange(PROPERTY_FILES, files.length, files.length+1);
			
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return false;
		}
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

	public boolean deleteFile(String selectedFile) {
		boolean result = (new File(currentDirectory+"/"+selectedFile)).delete();
		
		changes.firePropertyChange(PROPERTY_FILES, files.length, files.length-1);
		return result;
	}	
	
	public void deleteFolder(String name){
		deleteRecFolder(new File(currentDirectory+"/"+name));
		changes.firePropertyChange(PROPERTY_FILES, files.length, files.length-1);
	}

	public boolean renameFile(String oldName, String newName) {
		boolean result = (new File(currentDirectory+"/"+oldName)).renameTo(new File(currentDirectory+"/"+newName));
		
		if(result)
		changes.firePropertyChange(PROPERTY_FILES, files.length, files.length+1);
		
		return result;
		
	}
	public boolean renameFolder(String oldName, String newName){
		boolean result = (new File(currentDirectory+"/"+oldName)).renameTo(new File(currentDirectory+"/"+newName));
		
		if(result)
		changes.firePropertyChange(PROPERTY_FILES, files.length, files.length+1);
		
		return result;
	}
	//#if Partitions_Harddrive
	public List<String> getPartitions(){
		List<String> partitions = new ArrayList<String>();
		try {

			// Determine all the root paths for the system.
			File[] roots = File.listRoots();


			for (File root : roots) {
				partitions.add(root.getAbsolutePath());
			}


		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}

		return partitions;
	}
	// #endif

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
