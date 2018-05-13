/**
 * 
 */
package enterprise.binder.domain;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * @author ambarrana
 *
 */

@Getter
@Setter
@RequiredArgsConstructor
public class Email {

	private List<String> emailTo;
	private String subject;
	private String content;
	private String sendFrom;

}
