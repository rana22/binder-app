/**
 * 
 */
package enterprise.binder.service.util;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Component;
/**
 * @author ambarrana
 *
 */
@Component
public class FileHelper {
	
	public File createNewFile(String fileName) {
		return new File(fileName);
	}
	
	public Path createFiles (String filePath) throws IOException{
		return Files.createFile(Paths.get(filePath));
	}
	
	public Path returnPath(String sourceDirPath) {
		return Paths.get(sourceDirPath);
	}
	
	public ZipOutputStream getOutputStream(Path path) throws IOException{
		return new ZipOutputStream(Files.newOutputStream(path));
	}

}
