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
		/*s
		
		

		try{
			Files.walk(Paths.get(path)).forEach(filePath -> {
				try {
					BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);

					String[] file = {filePath.toString(), attr.lastAccessTime().toString(), (attr.isDirectory() ? "DIR" : "FILE"), ""+(int)(attr.size() / 1024)+"kb" };

					//DefaultTableModel model = (DefaultTableModel) table.getModel();
					files.add(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}

			});
		}catch(IOException ex){

		};
*/
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
