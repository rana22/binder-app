/**
 * 
 */
package enterprise.binder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import enterprise.binder.domain.UserProfile;

/**
 * @author ambarrana
 *
 */
public interface UserProfileRepository extends JpaRepository<UserProfile, Long>{

}
