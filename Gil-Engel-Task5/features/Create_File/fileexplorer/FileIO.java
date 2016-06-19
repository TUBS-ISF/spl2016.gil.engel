package fileexplorer;

import java.io.File;
import java.io.IOException;

/**
 * TODO description
 */
public class FileIO {
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
}