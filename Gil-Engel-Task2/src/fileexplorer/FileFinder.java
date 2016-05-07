package fileexplorer;
import java.util.List;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class FileFinder {
	public static List<String[]> getFiles(String path){
		List<String[]> files = new ArrayList<>();

		try{
		Files.walk(Paths.get(path)).forEach(filePath -> {
			try {
				BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);

				String[] file = {filePath.toString(), attr.lastAccessTime().toString(), (attr.isDirectory() ? "DIR" : "FILE"), ""+(int)(attr.size() / 1024)+"kb" };

				//DefaultTableModel model = (DefaultTableModel) table.getModel();
				files.add(file);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});
		}catch(IOException ex){
			
		};

		return files;
	}
}