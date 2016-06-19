package fileexplorer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO description
 */
public class FileIO {
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
}