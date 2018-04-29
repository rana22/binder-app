/**
 * 
 */
package enterprise.binder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import enterprise.binder.domain.Document;

/**
 * @author ambarrana
 *
 */
public interface DocumentRepository extends JpaRepository<Document ,Long>{

}
