/**
 * 
 */
package enterprise.binder.controller;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.jna.StringArray;

import enterprise.binder.service.ZipFolderService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ambarrana
 *
 */

@Slf4j
@RestController
@RequestMapping("/api/download/logs")
public class LogFolderController {
	
	private static final String CONTENT_TYPE = MediaType.APPLICATION_OCTET_STREAM_VALUE;
	
	private ZipFolderService zipFolderService;
	
	public LogFolderController(final ZipFolderService zipFolderService) {
		this.zipFolderService = zipFolderService;
	}
	
	@GetMapping
	public ResponseEntity<Resource> downloadLogFolder(){
		log.info("Getting logs from ");
		try {
			File file = zipFolderService.createFilesAndZipFolder();
			
			if(file != null) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.parseMediaType(CONTENT_TYPE));
				headers.add("Content-Disposition", String.format("attachement; filaname\"%s\"", file.getName()));
				
				return ResponseEntity.ok()
						.headers(headers)
						.contentLength(file.length())
						.contentType(MediaType.parseMediaType(CONTENT_TYPE))
						.body(new ByteArrayResource(Files.readAllBytes(Paths.get(file.getAbsolutePath()))));
						
				}
			} catch(IOException e) {
				log.debug("Exception while retriving log.zip file {}",e.getMessage());
				throw new UncheckedIOException(e);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
