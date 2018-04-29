/**
 * 
 */
package enterprise.binder.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import enterprise.binder.domain.Document;

/**
 * @author ambarrana
 *
 */
@RestController
@RequestMapping("/document")
public class DocumentController {
	
	private static final Logger log = LoggerFactory.getLogger(DocumentController.class);
	
	@GetMapping
	public ResponseEntity<List<Document>> getUserDocument(@RequestParam("userID") Long userId){
		log.info("Requesting all the document for the user {} ", userId);
		List<Document> userDocuments = new ArrayList<Document>();
		return ResponseEntity.ok(userDocuments);
	}
}
