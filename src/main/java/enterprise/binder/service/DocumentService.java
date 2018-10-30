/**
 *
 */
package enterprise.binder.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import enterprise.binder.domain.DocumentDetails;
import enterprise.binder.domain.UserProfile;

/**
 * @author ambarrana
 */
public abstract class DocumentService {

    public abstract boolean saveDocument(DocumentDetails documentDetails) throws IOException;

    public abstract Optional<DocumentDetails> findByOne(Long id);

    public abstract List<DocumentDetails> retrieveAllDocument();

}
