/**
 * 
 */
package enterprise.binder.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import enterprise.binder.domain.DocumentDetails;

/**
 * @author ambarrana
 *
 */
public interface DocumentDetailsRepository extends JpaRepository<DocumentDetails, Long>{
	
	
	Optional<DocumentDetails> findById(Long id);
	
	List<DocumentDetails> findAll();
	
//	List<DocumentDetails> findByUserId(Long userId);
}
