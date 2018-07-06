/**
 * 
 */
package enterprise.binder.service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.stream.Stream;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enterprise.binder.service.util.FileHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ambarrana
 *
 */
@Slf4j
@Service
public class ZipFolderService {
	
	private static final String BASE_URL = "var/logs";
	
	private static final String ZIP_URL = "var/log.zip";
	
	private FileHelper fileHelper;
	
	public ZipFolderService(final FileHelper fileHelper) {
		this.fileHelper = fileHelper;
	}
	
	public File createFilesAndZipFolder() {
		try {
			createFileToLogFolder("SystemProperties.txt", System.getProperties());
			createZipFolder(BASE_URL, ZIP_URL);
			
			return fileHelper.createNewFile(ZIP_URL);
		} catch (Exception ex) {
			log.warn("Exception while creating zip folder {}", ex.getMessage());
		}
		
		return null;
	}
	
	private void createFileToLogFolder(String fileName, Object data) {
		File file = fileHelper.createNewFile(BASE_URL +"/"+ fileName);
		if(data != null) {
			FileUtil.writeAsString(file, String.valueOf(data));
		}
	}
	
	private void createZipFolder(String sourceDirPath, String zipFilePath) throws IOException{
		File zipFile = fileHelper.createNewFile(zipFilePath);
		if(zipFile.getAbsolutePath().contains(ZIP_URL) && zipFile.exists()) {
			java.nio.file.Files.delete(Paths.get(ZIP_URL));
		}
		
		Path filePath = fileHelper.createFiles(zipFilePath);
		
		try(ZipOutputStream zs = fileHelper.getOutputStream(filePath);
			Stream<Path> stream = Files.walk(fileHelper.returnPath(sourceDirPath))){
			Path pp = fileHelper.returnPath(sourceDirPath);
			stream.filter(path -> !Files.isDirectory(path))
						.forEach(path->{
							ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
							try {
								zs.putNextEntry(zipEntry);
								Files.copy(path, zs);
								zs.closeEntry();
							} catch (IOException e) {
								log.warn("Exceptoin while iterating file under log folder {}", e.getMessage());
							}
							
						});
		}
	}
}
