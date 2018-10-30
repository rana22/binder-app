/**
 *
 */
package enterprise.binder.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import enterprise.binder.domain.DocumentCategory;
import enterprise.binder.domain.DocumentDetails;
import enterprise.binder.domain.UserProfile;
import enterprise.binder.repository.DocumentDetailsRepository;
import enterprise.binder.repository.DocumentRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ambarrana
 */
@Slf4j
@Service
public class DocumentServiceImpl extends DocumentService {

    private final DocumentDetailsRepository documentDetailsRepository;

    @Autowired
    public DocumentServiceImpl(final DocumentDetailsRepository documentDetailsRepository) {
        this.documentDetailsRepository = documentDetailsRepository;
    }
    
    /* method move file to their respective folder
     * @param file path to be copied
     */
    @Override
    public boolean saveDocument(DocumentDetails documentDetails) throws IOException {
    	documentDetailsRepository.save(documentDetails);
    	
    	
    	//move file to destination folder
    	
    	
    	
        return false;
    }

    /* (non-Javadoc)
     * @see enterprise.binder.service.DocumentService#findByOne()
     */
    @Override
    public Optional<DocumentDetails> findByOne(Long id) {
        Optional<DocumentDetails> document = documentDetailsRepository.findById(id);
        log.info("getting document {}");
        return document;
    }

    /* (non-Javadoc)
     * @see enterprise.binder.service.DocumentService#retriveAllDocument()
     */
    @Override
    public List<DocumentDetails> retrieveAllDocument() {
        return documentDetailsRepository.findAll();
    }
    
    
    private String getDestinationFolderPath(String category) {
//    	DocumentCategory category = DocumentCategory.class.
//    	switch(category) {
//    		case DocumentCategory.
//    	}
    	return "";
    }

}
