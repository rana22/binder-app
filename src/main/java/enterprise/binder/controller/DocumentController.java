/**
 * 
 */
package enterprise.binder.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import enterprise.binder.domain.Document;
import enterprise.binder.domain.DocumentDetails;
import enterprise.binder.domain.Email;
import enterprise.binder.service.DocumentServiceImpl;
import enterprise.binder.service.EmailServiceImpl;
import enterprise.binder.service.JobService;
import enterprise.binder.service.SimpleJob;

/**
 * @author ambarrana
 *
 */
@RestController
@RequestMapping("/document")
public class DocumentController {
	
	private static final Logger log = LoggerFactory.getLogger(DocumentController.class);
	
//	private final EmailServiceImpl emailService;
	
	@Autowired
	@Lazy
	private JobService jobService;
	
	@Autowired
	private DocumentServiceImpl documentService;
	
	@GetMapping
	public ResponseEntity<List<Document>> getUserDocument(@RequestParam("userID") Long userId){
		log.info("Requesting all the document for the user {} ", userId);
		List<Document> userDocuments = new ArrayList<Document>();
		return ResponseEntity.ok(userDocuments);
	}
	
	@PostMapping
	public ResponseEntity<DocumentDetails> postUserDocument(@RequestBody DocumentDetails document){
		log.info("Inserting new user document ");
//		documentService.saveDocument(document);
		return ResponseEntity.ok(document);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity getDocumentById(@PathVariable("id") Long id) {
		log.info("Document for id {}", id);
		Optional<DocumentDetails> document = documentService.findByOne(id);
		return ResponseEntity.ok(document);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<DocumentDetails>> getUserDocuments(){
		List<DocumentDetails>  documents = documentService.retrieveAllDocument();
		log.info("get all the documents {}", documents.size());
		return ResponseEntity.ok(documents);
	}
	
	@GetMapping("/trigger")
	public ResponseEntity getTrigger() {
		log.info("triggering set one time cron job");
	    Date date = new Date();  
		boolean status = jobService.scheduleOneTimeJob("execute-task-1", SimpleJob.class, date);
		return ResponseEntity.ok(status);
	}
}
