/**
 * 
 */
package enterprise.binder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import enterprise.binder.domain.AuditActivity;

/**
 * @author ambarrana
 *
 */
public interface AuditActivityRepository extends JpaRepository<AuditActivity, Long>{
	
	
}
